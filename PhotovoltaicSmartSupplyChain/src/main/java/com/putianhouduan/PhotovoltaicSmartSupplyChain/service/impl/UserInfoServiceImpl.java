package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.UserInfoDto;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserInfoService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 林圣涛
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserService userService;
    @Override
    public Integer getMerchantId() {
        return this.userInfoDto().getMerchantId();
    }

    public UserInfoDto userInfoDto(){
        //获取当前认证消息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        //获取自定义的用户详细信息
        com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User account = userService.findUserByNameOrEmail(user.getUsername());


        UserInfoDto userInfoDto=new UserInfoDto();
        userInfoDto.setMerchantId(account.getMerchantId());
        userInfoDto.setRole(account.getRole().toString());
        userInfoDto.setUsername(account.getUsername());
        userInfoDto.setEmail(account.getEmail());
        return userInfoDto;
    }
}
