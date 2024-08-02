package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;

import java.util.Date;


/**
 * @author 林圣涛
 */
public interface OrderService extends IService<Order>{

    Boolean convertPreOrderToOrder(Integer preOrderId , Integer buyerId , Date transactionDate);

}
