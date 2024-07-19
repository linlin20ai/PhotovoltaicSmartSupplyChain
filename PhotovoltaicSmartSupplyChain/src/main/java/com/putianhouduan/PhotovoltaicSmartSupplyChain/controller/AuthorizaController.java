package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 林圣涛
 */
@RestController
@RequestMapping("/api/auth")
public class AuthorizaController {

    @Resource
    UserService service;

    @GetMapping("/ask-code")
    public CommonResult<Void> askVerifyCode(@RequestParam String email,
                                            @RequestParam String type,
                                            HttpServletRequest request){
        String message=service.registerEmailVerifyCode(type,email,request.getRemoteAddr());
        return message == null ? CommonResult.success(null) : CommonResult.failed(message);
    }


}
