package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.OrderMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.PreOrderMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author 林圣涛
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    PreOrderMapper preOrderMapper;

    @Resource
    OrderMapper orderMapper;

    @Override
    public Boolean convertPreOrderToOrder(Integer preOrderId, Integer buyerId, Date transactionDate) {

        PreOrder preOrder = preOrderMapper.selectById(preOrderId);
        if(preOrder == null ){
            return false;
        }

        // 创建订单
        Order order = getOrder(buyerId, transactionDate, preOrder);

        //保存订单
        orderMapper.insert(order);

        //更新申请交易订单的状态
        //preOrder.setStatus("converted");
        //preOrder.setUpdateAt(new Date());
        UpdateWrapper<PreOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("pre_order_id",preOrder.getPreOrderId()).set("updated_at",new Date());

        return true;
    }

    //
    private static Order getOrder(Integer buyerId, Date transactionDate, PreOrder preOrder) {
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setSellerId(preOrder.getSellerId());
        order.setItem(preOrder.getItem());
        order.setQuantity(preOrder.getQuantity());
        order.setPricePerUnit(preOrder.getPricePerUnit());
        order.setTransactionDate(transactionDate);
        order.setLocation(preOrder.getLocation());
        order.setStatus("start");
        order.setCreatedAt(new Date());
        order.setUpdateAt(new Date());
        return order;
    }
}
