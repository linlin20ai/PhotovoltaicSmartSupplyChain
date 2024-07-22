package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import org.springframework.security.core.userdetails.User;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 林圣涛
 */
@RestController
@RequestMapping("/api/text")
public class TestController {

    @Resource
    UserService userService;
    @GetMapping("/hello")
    public String test(){
        return "Hello World";
    }

    @GetMapping("/info")
    public com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User getUserInfo(){
        //获取当前认证消息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        //获取自定义的用户详细消息
        com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User account = userService.findUserByNameOrEmail(user.getUsername());

        com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User user1=new com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User();
        user1.setUsername(account.getUsername());
        user1.setEmail(account.getEmail());
        user1.setRole(account.getRole());
        user1.setMerchantId(account.getMerchantId());
        return user1;
    }
}
