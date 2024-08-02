package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.MerchantBalances;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.MerchantBalancesMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.MerchantBalancesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 林圣涛
 */
@Service
public class MerchantsBalancesServiceImpl extends ServiceImpl<MerchantBalancesMapper, MerchantBalances> implements MerchantBalancesService {

    @Resource
    MerchantBalancesMapper merchantBalancesMapper;


    @Override
    public Boolean subMerchantBalances(Double payAmount, Integer userId) {
        MerchantBalances merchantBalances = merchantBalancesMapper.selectById(userId);
        Double balances = merchantBalances.getBalance();

        if (balances >= payAmount) {
            UpdateWrapper<MerchantBalances> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("merchant_id", userId).set("balance", balances - payAmount).set("last_updated", new Date());
            int updateResult = merchantBalancesMapper.update(null, updateWrapper);
            return updateResult > 0;
        }
        return false;
    }


    @Override
    public Boolean addMerchantBalances(Double payAmount, Integer userId) {
        MerchantBalances merchantBalances = merchantBalancesMapper.selectById(userId);
        Double balances = merchantBalances.getBalance();

        UpdateWrapper<MerchantBalances> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("merchant_id",userId).set("balance",balances+payAmount).set("last_updated",new Date());
        int updateResult = merchantBalancesMapper.update(null, updateWrapper);
        return updateResult > 0;
    }
}
