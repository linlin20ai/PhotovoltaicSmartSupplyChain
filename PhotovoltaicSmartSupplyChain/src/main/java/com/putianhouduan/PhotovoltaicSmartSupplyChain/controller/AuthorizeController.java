package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.ConfirmResetVO;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.EmailRegisterVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.EmailResetVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.function.Function;
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
        return this.messageHandle(emailRegisterVo,service::registerEmailAccount);
    }
    @PostMapping("/reset-confirm")
    public CommonResult<Void> resetConfirm(@RequestBody @Valid ConfirmResetVO confirmResetVO){
        return  this.messageHandle(confirmResetVO,service::resetConfirm);
    }

    @PostMapping("/reset-password")
    public CommonResult<Void> resetConfirm(@RequestBody @Valid EmailResetVo emailResetVo){
        return  this.messageHandle(emailResetVo,service::resetEmailAccountPassword);
    }

    private <T> CommonResult<Void> messageHandle(T vo, Function<T,String> function){
        return messageHandle(()->function.apply(vo));
    }

    private CommonResult<Void> messageHandle(Supplier<String> action){
        String message = action.get();
        return message == null ? CommonResult.success(null):CommonResult.failed(message);
    }


}
