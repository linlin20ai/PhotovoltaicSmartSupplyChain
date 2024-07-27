package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;

import java.util.List;

/**
 * @author 林圣涛
 */
public interface OrderQueryService {

    List<Long> getMonthlyOrders();

    Long getSum();
}
