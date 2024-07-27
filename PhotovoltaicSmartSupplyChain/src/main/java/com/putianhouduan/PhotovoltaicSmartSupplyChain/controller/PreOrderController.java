package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.IErrorCode;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.PreOrderService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "PreOrderController",value = "交易申请订单申请类")
@RequestMapping("/preOrder")
public class PreOrderController {

    @Resource
    PreOrderService preOrderService;

    @Resource
    UserInfoService userInfoService;

    @RequestMapping(value = "/showAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PreOrder>> showPreOrder(){
        List<PreOrder> list = preOrderService.list();
        if(list != null){
            return CommonResult.success(list,"这是全部信息");
        }else {
            return CommonResult.failed("请求失败");
        }
    }

    @RequestMapping(value = "/creatPreOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> createPreOrder(@RequestBody PreOrder preOrder){
            preOrder.setSellerId(userInfoService.getMerchantId());
            return preOrderService.createPreOrder(preOrder);

    }
}
