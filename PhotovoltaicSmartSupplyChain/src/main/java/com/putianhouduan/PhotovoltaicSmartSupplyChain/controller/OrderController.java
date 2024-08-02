package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;


import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Merchants;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.response.OrderSumVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.response.OrderVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.MerchantsService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderQueryService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import io.swagger.annotations.Api;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author 林圣涛
 */
@Controller
@Api(tags = "OrderController",value = "交易市场")
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @Resource
    OrderQueryService orderQueryService;

    @Resource
    MerchantsService merchantsService;


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
    public CommonResult<List<OrderVo>> getOrderList(){
        List<Order> list = orderService.list();
        List<Order> collect = new ArrayList<>(Optional.ofNullable(list)
                .orElse(Collections.emptyList()));
        List<OrderVo> res = new ArrayList<>();
        collect.forEach(order -> {
            Merchants buyerMerchant = merchantsService.getById(order.getBuyerId());
            Merchants sellMerchant = merchantsService.getById(order.getSellerId());
            OrderVo orderVo = new OrderVo(order.getOrderId(),buyerMerchant.getName(),
                    sellMerchant.getName(),order.getItem(),order.getQuantity(),order.getPricePerUnit(),
                    order.getTotalAmount(),order.getTransactionDate(),order.getLocation(),order.getStatus(),
                    order.getCreatedAt(),order.getUpdateAt());
            res.add(orderVo);
        });
        return collect.isEmpty() ? CommonResult.failed("系统出了点小问题，请联系管理员解决") : CommonResult.success(res);
    }

    @ApiOperation("创建完成订单")
    @RequestMapping(value = "/cr eateOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Order> creatOrder( @RequestBody Order order){
        boolean save = orderService.save(order);
        if(save){
            return CommonResult.success(order,"订单完成");
        }
        return CommonResult.success(null,"订单完成有误请及时联系管理员");
    }


    @ApiOperation("查询总交易量")
    @RequestMapping(value = "/listorder",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Long>> getMonthlyOrders() {
        List<Long> monthlyOrders = orderQueryService.getMonthlyOrders();
        return CommonResult.success(monthlyOrders);
    }



    @RequestMapping(value = "/sum",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OrderSumVo>  getSum(){
        List<Order> list = orderService.list();
        OrderSumVo orderSumVo = new OrderSumVo();

        // 使用流处理计算总和
        Double totalSum = list.stream()
                .map(Order::getTotalAmount)
                .filter(Objects::nonNull)
                // 过滤空值
                .reduce(0.0, Double::sum);

        orderSumVo.setSum(totalSum);

        return CommonResult.success(orderSumVo);
    }
}
