package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderQueryService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 林圣涛
 */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Resource
    private OrderService orderService;

    @Override
    public List<Long> getMonthlyOrders() {
        List<Long> monthlyOrders = new ArrayList<>(12);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        // January
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        for (int i = 0; i < 12; i++) {
            Date startDate = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            // Reset to the first day of the month
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date endDate = calendar.getTime();

            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            long count = orderService.count(queryWrapper.between("transaction_date", startDate, endDate));
            monthlyOrders.add(count);
        }

        return monthlyOrders;
    }

    @Override
    public Long getSum() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        return null;
    }
}
