package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.MerchantBalances;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.MerchantBalancesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "MerchantsBalancesController",value = "用户余额")
@RequestMapping("/Balances")
public class MerchantsBalancesController {

    @Resource
    private MerchantBalancesService merchantBalancesService;



    @ApiOperation("根据id查询用户余额")
    @RequestMapping("/id")
    @ResponseBody
    public CommonResult<MerchantBalances> getById(Integer merchantId){
        MerchantBalances byId = merchantBalancesService.getById(merchantId);
        if(byId != null ){
            return CommonResult.success(byId);
        }else {
            return CommonResult.failed();
        }
    }
}
