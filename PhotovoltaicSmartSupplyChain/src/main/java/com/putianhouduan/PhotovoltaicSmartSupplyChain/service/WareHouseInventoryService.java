package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouseInventory;

import java.util.List;

/**
 * @author 林圣涛
 */
public interface WareHouseInventoryService extends IService<WareHouseInventory> {


    List<WareHouseInventory> selectByMerchantId(Integer merchantId);
}
