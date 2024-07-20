package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;


import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import io.swagger.annotations.Api;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "OrderController",value = "交易市场")
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;


    @ApiOperation("根据id查询已接受订单")
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Order> getAcceptedOrders(int id){
        Order byId = orderService.getById(id);
        if(byId != null){
            return CommonResult.success(byId);
        }else {
            return CommonResult.failed("请仔细检查id或者联系管理员");
        }
    }

    @ApiOperation("查询已接受订单")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Order>> getOrderList(){
        List<Order> list = orderService.list();
        List<Order> collect = new ArrayList<>(Optional.ofNullable(list)
                .orElse(Collections.emptyList()));
        return collect.isEmpty() ? CommonResult.failed("Failed to retrieve order list") : CommonResult.success(collect);
    }
}
