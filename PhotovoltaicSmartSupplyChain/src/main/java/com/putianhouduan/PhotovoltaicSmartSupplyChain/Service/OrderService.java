package com.putianhouduan.PhotovoltaicSmartSupplyChain.Service;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dao.OrderDao;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.preOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林圣涛
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    /**
     * 查询交易市场已接受订单或被接受订单
    * */
    public List<Order> getAcceptedOrders(int id){
        return orderDao.getAcceptedOrders(id);
    }

    /**
    * 根据订单id查询订单详细
    * */
    public Order getOrderById(int id){
        return orderDao.getOrderById(id);
    }

    /**
     * 发布订单
    * */
    public CommonResult postOrder(int id, String item, int quantity, double pricePerUnit,String location){
        return orderDao.postOrder( id, item, quantity,  pricePerUnit,location);
    }


    /**
     * 5.接受订单
     * 先查询相关的订单
     * 增加到交易成功表
     * 修改申请交易表
     * 完成
     * */
    public CommonResult acceptsOrder(int preOrderId,int buyerId){
        //先查询交易市场中申请交易的订单
        preOrder preOrder= orderDao.getPreOrderById(preOrderId);
        if(preOrder==null){
            return CommonResult.failed();
        }
        CommonResult commonResult=orderDao.addOrder(buyerId,preOrder.getSellerId(),preOrder.getItem(),preOrder.getQuantity(),preOrder.getPricePerUnit(),preOrder.getLocation(),preOrder.getStatus());
        //添加到交易表
        if(commonResult.equals(CommonResult.failed())){
            return CommonResult.failed();
        }
        //修改申请交易表状态为（已经确认）
        return orderDao.changePreOrder(preOrderId,"confirmed");
    }
}
