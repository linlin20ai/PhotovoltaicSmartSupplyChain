package com.putianhouduan.PhotovoltaicSmartSupplyChain.dao;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.preOrder;

import java.util.Date;
import java.util.List;

/**
 * @author 林圣涛
 * 订单Dao
 */
public interface OrderDao {
    /**
    * 1.无条件查询接受订单（返回订单列表（已经交易完的））
    * */
    List<Order> getAcceptedOrders(int id);
    /**
    * 2.根据订单id进行查询(已经交易完的）
    * */
    Order getOrderById(int id);
    /**
    * 3.订单退货
    * */
    int orderReturn(int id);
    /**
    * 4.发布订单
    * */
    CommonResult postOrder(int id, String item, int quantity, double pricePerUnit,String location);



    /**
    * 5.查询申请交易的订单（根据订单id）
    * */
    preOrder getPreOrderById(int preOrderId);
    /**
    * 6.order表的添加
    * */
    CommonResult addOrder(int buyerId,int sellerId,String item,int quantity,double pricePerUnit,String location,String status);
    /**
    * 7.修改申请交易的订单状态
     * （根据订单id进行状态修改）
    * */
    CommonResult changePreOrder(int preOrderId,String status);

}
