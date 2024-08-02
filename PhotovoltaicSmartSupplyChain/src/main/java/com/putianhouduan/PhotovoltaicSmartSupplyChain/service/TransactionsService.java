package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Transactions;

/**
 * @author 林圣涛
 */
public interface TransactionsService extends IService<Transactions> {
    Boolean createPreOrderTransaction(PreOrder preOrder , Integer userId ,Double  balance , Double paySum);
}
