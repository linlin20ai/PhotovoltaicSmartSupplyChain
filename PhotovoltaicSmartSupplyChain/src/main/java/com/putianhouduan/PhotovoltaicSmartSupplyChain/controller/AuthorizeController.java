package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.EmailRegisterVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.function.Supplier;

/**
 * @author 林圣涛
 */

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    @Resource
    UserService service;

    @GetMapping("/ask-code")
    public CommonResult<Void> askVerifyCode(@RequestParam @Email String email,
                                            @RequestParam  @Pattern(regexp = "(register|reset)") String type,
                                            HttpServletRequest request){
        String message=service.registerEmailVerifyCode(type,email,request.getRemoteAddr());
        return message == null ? CommonResult.success(null) : CommonResult.failed(message);
    }

    @PostMapping("/register")
    public CommonResult<Void> register(@RequestBody @Valid EmailRegisterVo emailRegisterVo){
        return this.messageHandle(()->service.registerEmailAccount(emailRegisterVo));
    }

    private CommonResult<Void> messageHandle(Supplier<String> action){
        String message = action.get();
        return message == null ? CommonResult.success(null):CommonResult.failed(message);
    }


}
