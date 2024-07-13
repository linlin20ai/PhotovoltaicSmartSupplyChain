package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.ConfirmResetVO;
import com.example.entity.vo.request.EmailRegisterVo;
import com.example.entity.vo.request.EmailResetVo;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
import com.example.utils.Const;
import com.example.utils.FlowUtils;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 林圣涛
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    AmqpTemplate amqpTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    FlowUtils flowUtils;

    @Resource
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account=this.findAccountByNameOrEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    @Override
    public Account findAccountByNameOrEmail(String text) {
        return this.query()
                .eq("username",text).or()
                .eq("email",text)
                .one();
    }

    @Override
    public String registerEmailVerifyCode(String type, String email, String ip) {
        synchronized (ip.intern()){
            if(!this.verifyLimit(ip)){
                return "请求频繁，请稍后再试";
            }
            Random random=new Random();
            int code = random.nextInt(899999)+100000;
            Map<String,Object> data=Map.of("type",type,"email",email,"code",code);
            amqpTemplate.convertAndSend("mail",data);
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA+email,String.valueOf(code), 3,TimeUnit.MINUTES);
            return null;
        }
    }

    @Override
    public String registerEmailAccount(EmailRegisterVo emailRegisterVo) {
        String email=emailRegisterVo.getEmail();
        String key=Const.VERIFY_EMAIL_DATA+email;
        String code=stringRedisTemplate.opsForValue().get(key);
        String username=emailRegisterVo.getUsername();
        if(code == null){
            return "请先获取验证码";
        }
        if(!code.equals(emailRegisterVo.getCode())){
            return "验证码输入错误，请重新输入";
        }
        if(this.exciteAccountByEmail(email)){
            return "该邮箱已经被注册过了";
        }
        if(this.exciteAccountByUsername(username)){
            return "该用户名已经被占用,请更换一个或者联系平台管理员";
        }
        String password=passwordEncoder.encode(emailRegisterVo.getPassword());
        Account account=new Account(null,username,password,email,"user",new Date());
        if (this.save(account)) {
            stringRedisTemplate.delete(key);
            return null;
        }
        return "内部错误，请联系管理员";
    }

    @Override
    public String resetConfirm(ConfirmResetVO confirmResetVO) {
        String email = confirmResetVO.getEmail();
        String code = stringRedisTemplate.opsForValue().get(Const.VERIFY_EMAIL_DATA + email);
        if(code == null){
            return "请先填写邮件验证码！";
        }
        if(code.equals(confirmResetVO.getCode())){
            return "验证码错误，请重新填写";
        }
        return null;
    }

    @Override
    public String resetEmailAccountPassword(EmailResetVo emailResetVo) {
        String email=emailResetVo.getEmail();
        String verify=this.resetConfirm(new ConfirmResetVO(email,emailResetVo.getCode()));
        if(verify != null){
            return  verify;
        }
        String password=passwordEncoder.encode(emailResetVo.getPassword());
        boolean update=this.update().eq("email",email).set("password",password).update();
        if(update){
            stringRedisTemplate.delete(Const.VERIFY_EMAIL_DATA + email);
        }
        return null;
    }

    private boolean exciteAccountByEmail(String email){
        return this.baseMapper.exists(Wrappers.<Account>query().eq("email",email));
    }

    private boolean exciteAccountByUsername(String username){
        return this.baseMapper.exists(Wrappers.<Account>query().eq("username",username));
    }

    private boolean verifyLimit(String ip){
        String key=Const.VERIFY_EMAIL_LIMIT+ip;
        return flowUtils.limitOnceCheck(key,60);
    }
}
