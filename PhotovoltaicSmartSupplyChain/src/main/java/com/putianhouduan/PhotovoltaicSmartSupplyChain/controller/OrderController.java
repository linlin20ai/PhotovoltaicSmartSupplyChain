package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;


import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import io.swagger.annotations.Api;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "OrderController",description = "交易市场")
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;


    @ApiOperation("根据id查询已接受订单")
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAcceptedOrders(int id){
        Order byId = orderService.getById(id);
        if(byId != null){
            return CommonResult.success(byId);
        }else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询已接受订单")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getOrderList(){
        List<Order> list = orderService.list();
        if(list != null){
            return CommonResult.success(list);
        }else {
            return CommonResult.failed();
        }
    }
}
