package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.UserMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 林圣涛
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

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
}
