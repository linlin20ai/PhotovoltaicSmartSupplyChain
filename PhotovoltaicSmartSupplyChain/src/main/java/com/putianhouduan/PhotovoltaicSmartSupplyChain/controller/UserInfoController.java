package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.IErrorCode;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.UserInfoDto;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "UserInfoController",value = "用户信息")
@RequestMapping("/userDto")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;


    @RequestMapping(value = "/getUserInfo" , method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UserInfoDto> getUserInfo() {
        return Optional.ofNullable(userInfoService.userInfoDto())
                .map(CommonResult::success)
                .orElseGet(() -> CommonResult.failed("请联系管理员"));
    }

}
