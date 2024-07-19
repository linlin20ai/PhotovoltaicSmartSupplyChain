package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.OrderMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import org.springframework.stereotype.Service;


/**
 * @author 林圣涛
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
