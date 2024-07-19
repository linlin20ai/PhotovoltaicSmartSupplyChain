package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller.exception;


import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * @author 林圣涛
 */
@Slf4j
@RestControllerAdvice
public class ValidationController {

    public CommonResult<Void> validateException(ValidationException exception){
        log.warn("Resolve [{},{}]",exception.getClass().getName(),exception.getMessage());
        return CommonResult.failed("请求参数有误");
    }
}
