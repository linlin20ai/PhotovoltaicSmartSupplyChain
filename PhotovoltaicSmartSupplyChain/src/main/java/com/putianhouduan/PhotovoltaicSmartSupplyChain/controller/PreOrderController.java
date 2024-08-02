package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.IErrorCode;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Materials;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Merchants;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.PreOrderRequestVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.response.PreOrderVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.*;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    MerchantsService merchantsService;

    @Resource
    MerchantBalancesService merchantBalancesService;

    @Resource
    TransactionsService transactionsService;

    @Resource
    OrderService orderService;

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

    @RequestMapping(value = "/findPreOrderById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PreOrderVo> findById(int preId){
        PreOrder byId = preOrderService.getById(preId);
        if(byId == null){
            return CommonResult.failed("请确实交易订单是否还存在");
        }
        Merchants sellerName = merchantsService.getById(byId.getSellerId());
        PreOrderVo preOrderVo = new PreOrderVo();
        preOrderVo.setPreOrderId(byId.getPreOrderId());
        preOrderVo.setSellerName(sellerName.getName());
        preOrderVo.setItem(byId.getItem());
        preOrderVo.setQuantity(byId.getQuantity());
        preOrderVo.setPricePerUnit(byId.getPricePerUnit());
        preOrderVo.setLocation(byId.getLocation());
        preOrderVo.setStatus(byId.getStatus());
        preOrderVo.setTransactionDate(byId.getExpectedTransactionDate());
        preOrderVo.setPricePerUnit(byId.getPricePerUnit());
        preOrderVo.setEstimatedTotalAmount(byId.getEstimatedTotalAmount());
        return CommonResult.success(preOrderVo);
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> payPreOrder(@RequestBody PreOrderRequestVo requestVo){
        Integer preOrderId = requestVo.getPreId();
        PreOrder preOrder = preOrderService.getById(preOrderId);
        if(preOrder == null ){
            return CommonResult.failed("请确认交易的订单是否还处于在售状态");
        }
        int buyerId = userInfoService.getMerchantId();
        int sellerId = preOrder.getSellerId();
        if(sellerId == buyerId){
            return CommonResult.failed("您不能自己交易，请仔细检查订单信息");
        }
        Double balance = merchantBalancesService.getById(buyerId).getBalance();
        Double paySum = preOrder.getEstimatedTotalAmount();
        if(balance >= paySum){
            if(orderService.convertPreOrderToOrder(preOrderId,buyerId,new Date() )
                    && transactionsService.createPreOrderTransaction(preOrder,buyerId,balance,paySum)){
                if(merchantBalancesService.subMerchantBalances(paySum,buyerId)
                        &&merchantBalancesService.addMerchantBalances(paySum,preOrder.getSellerId())){
                    preOrder.setStatus("converted");
                    return CommonResult.success("交易成功快去和卖家协商物流的处理吧！");
                }
                return CommonResult.failed("交易失败，稍后重试！");
            }
           return CommonResult.failed("交易失败，请稍后重试");
        }else {
            return CommonResult.failed("交易失败，余额不足");
        }

    }
}
