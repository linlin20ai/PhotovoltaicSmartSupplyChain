package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.MerchantBalances;


/**
 * @author 林圣涛
 */
public interface MerchantBalancesService extends IService<MerchantBalances> {
    Boolean addMerchantBalances(Double payAmount,Integer userId);

    Boolean subMerchantBalances(Double payAmount , Integer userId);
}
