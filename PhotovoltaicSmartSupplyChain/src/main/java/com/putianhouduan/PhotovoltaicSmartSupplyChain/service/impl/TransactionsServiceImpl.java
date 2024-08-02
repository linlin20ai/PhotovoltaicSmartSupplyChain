package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Transactions;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.TransactionsMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.TransactionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 林圣涛
 */
@Service
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions> implements TransactionsService {


    @Resource
    TransactionsMapper transactionsMapper;

    @Override
    public Boolean createPreOrderTransaction(PreOrder preOrder, Integer userId, Double balance, Double paySum) {
        if(paySum > balance){
            return false;
        }

        Integer sellerId =  preOrder.getSellerId();

        Transactions transaction = new Transactions();
        transaction.setSellerId(sellerId);
        transaction.setBuyerId(userId);
        transaction.setCurrency("CNY");
        transaction.setAmount(paySum);
        transaction.setStatus("true");
        transaction.setTransactionDate(new Date());

        transactionsMapper.insert(transaction);
        return true;
    }
}
