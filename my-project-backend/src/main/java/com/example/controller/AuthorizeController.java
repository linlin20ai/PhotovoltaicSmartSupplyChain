package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.vo.request.EmailRegisterVo;
import com.example.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;


/**
 * @author 林圣涛
 */
@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AccountService service;

    @GetMapping("/ask-code")
    public RestBean<Void> askVerifyCode(@RequestParam @Email String email,
                                        @RequestParam @Pattern(regexp = "(register|reset)") String type,
                                        HttpServletRequest request){
        return this.messageHandle(()->
                        service.registerEmailVerifyCode(type,email,request.getRemoteAddr()));
    }


    @PostMapping("/register")
    public RestBean<Void> register(@RequestBody @Valid EmailRegisterVo emailRegisterVo){
        return this.messageHandle(()->service.registerEmailAccount(emailRegisterVo));
    }

    private RestBean<Void> messageHandle(Supplier<String> action){
        String message = action.get();
        return message == null ? RestBean.success():RestBean.failure(400,message);
    }

}
