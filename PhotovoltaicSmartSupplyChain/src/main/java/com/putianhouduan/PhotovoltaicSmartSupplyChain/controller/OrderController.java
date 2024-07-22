package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;


import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import io.swagger.annotations.Api;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
        return Optional.ofNullable(orderService.getById(id))
                .map(CommonResult::success)
                .orElseGet(()->CommonResult.failed("请仔细检查您输入的id是否有误，或者请联系管理员"));
    }

    @ApiOperation("查询已接受订单")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Order>> getOrderList(){
        List<Order> list = orderService.list();
        List<Order> collect = new ArrayList<>(Optional.ofNullable(list)
                .orElse(Collections.emptyList()));
        return collect.isEmpty() ? CommonResult.failed("系统出了点小问题，请联系管理员解决") : CommonResult.success(collect);
    }

    @ApiOperation("创建完成订单")
    @RequestMapping(value = "/createOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Order> creatOrder( @RequestBody Order order){
        boolean save = orderService.save(order);
        if(save){
            return CommonResult.success(order,"订单完成");
        }
        return CommonResult.success(null,"订单完成有误请及时联系管理员");
    }
}
