package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.preOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.preOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    preOrderService preOrderService;

    @ApiOperation("查询已接受订单")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAcceptedOrders(int id){
        try{
            List<Order> list =  orderService.getAcceptedOrders(id);
            return CommonResult.success(list);
        }catch (Error e){
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 根据详细的订单id来查询订单的详情；
    * */
    @ApiOperation("查询订单详情")
    @RequestMapping(value = "/byId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getOrderById(int id){
        Order byId = orderService.getById(id);
        if(byId != null){
            return CommonResult.success(byId);
        }
        return CommonResult.failed();
    }



    @ApiOperation("发布订单")
    @RequestMapping(value = "/postOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult postOrder(@RequestBody preOrder preOrder){
            boolean save = preOrderService.save(preOrder);
            if (save){
                return CommonResult.success(save);
            }else {
                return CommonResult.failed();
            }
    }



   /* @ApiOperation("接受订单")
    @RequestMapping(value = "/acceptsOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult acceptsOrder(int preOrderId, int buyerId){
        return orderService.acceptsOrder(preOrderId,buyerId);
    }*/


}
