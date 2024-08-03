package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;

/**
 * @author 林圣涛
 */
public interface PreOrderService extends IService<PreOrder> {

     CommonResult<String> createPreOrder(PreOrder preOrder);


     void updateStatue(String statue , Integer preId);


}
