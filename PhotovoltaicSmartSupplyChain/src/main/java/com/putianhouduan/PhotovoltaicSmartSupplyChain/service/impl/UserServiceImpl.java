package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util.Const;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util.FlowUtils;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.EmailRegisterVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.UserMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 林圣涛
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

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
        User account=this.findUserByNameOrEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(account.getPassword())
                .roles(String.valueOf(account.getRole()))
                .build();
    }
    @Override
    public User findUserByNameOrEmail(String text) {
        return this.query()
                .eq("username",text).or()
                .eq("email",text)
                .one();
    }

    @Override
    public String registerEmailVerifyCode(String type, String email, String ip) {
        synchronized ((ip.intern())){
            if(!this.verifyLimit(ip)){
                return "请求频繁，请稍后再试";
            }
            Random random = new Random();
            int code = random.nextInt(899999)+100000;
            Map<String, Object> data = new HashMap<>();
            data.put("type", type);
            data.put("email", email);
            data.put("code", code);
            amqpTemplate.convertAndSend("mail",data);
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA+email,String.valueOf(code),3, TimeUnit.MINUTES);
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
        User account=new User(null,null,username,password,1,1,email);
        if (this.save(account)) {
            stringRedisTemplate.delete(key);
            return null;
        }
        return "内部错误，请联系管理员";
    }

    private boolean exciteAccountByEmail(String email){
        return this.baseMapper.exists(Wrappers.<User>query().eq("email",email));
    }

    private boolean exciteAccountByUsername(String username){
        return this.baseMapper.exists(Wrappers.<User>query().eq("username",username));
    }


    private boolean verifyLimit(String ip){
        String key= Const.VERIFY_EMAIL_LIMIT+ip;
        return flowUtils.limitOnceCheck(key,60);
    }
}
