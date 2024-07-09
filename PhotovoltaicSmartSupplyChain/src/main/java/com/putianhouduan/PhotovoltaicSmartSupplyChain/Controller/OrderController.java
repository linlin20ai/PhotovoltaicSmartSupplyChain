package com.putianhouduan.PhotovoltaicSmartSupplyChain.Controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.Service.OrderService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "OrderController",description = "交易市场")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @ApiOperation("查询已接受订单")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getAcceptedOrders(int id){
        return orderService.getAcceptedOrders(id);
    }
    @ApiOperation("查询订单详情")
    @RequestMapping(value = "/byId",method = RequestMethod.GET)
    @ResponseBody
    public Order getOrderById(int id){
        return orderService.getOrderById(id);
    }
    @ApiOperation("发布订单")
    @RequestMapping(value = "/postOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult postOrder(int id, String item, int quantity, double pricePerUnit,String location){
        return orderService.postOrder( id,  item, quantity, pricePerUnit, location);
    }
    @ApiOperation("接受订单")
    @RequestMapping(value = "/acceptsOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult acceptsOrder(int preOrderId, int buyerId){
        return orderService.acceptsOrder(preOrderId,buyerId);
    }
}
