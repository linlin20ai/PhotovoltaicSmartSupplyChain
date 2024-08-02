package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WarehouseInventoryChangeLog;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.WarehouseInventoryChangeVo;

/**
 * @author 林圣涛
 */
public interface WareHouseInventoryChangeLogService extends IService<WarehouseInventoryChangeLog> {
    WarehouseInventoryChangeLog VoToDto(WarehouseInventoryChangeVo warehouseInventoryChangeVo);
}
