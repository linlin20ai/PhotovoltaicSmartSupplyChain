package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WarehouseInventoryChangeLog;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.WarehouseInventoryChangeVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.WareHouseInventoryChangeLogMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserInfoService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHouseInventoryChangeLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 林圣涛
 */
@Service
public class WareHouseInventoryChangeLogServiceImpl extends ServiceImpl<WareHouseInventoryChangeLogMapper, WarehouseInventoryChangeLog> implements WareHouseInventoryChangeLogService {

    @Resource
    UserInfoService userInfoService;
    @Override
    public WarehouseInventoryChangeLog VoToDto(WarehouseInventoryChangeVo warehouseInventoryChangeVo) {

        WarehouseInventoryChangeLog warehouseInventoryChangeLog = new WarehouseInventoryChangeLog();


        warehouseInventoryChangeLog.setInventoryId(warehouseInventoryChangeVo.getInventoryId());
        warehouseInventoryChangeLog.setChangeType(warehouseInventoryChangeVo.getChangeType());
        warehouseInventoryChangeLog.setChangeQuantity(warehouseInventoryChangeVo.getChangeQuantity());
        warehouseInventoryChangeLog.setOrderId(warehouseInventoryChangeVo.getOrderId());
        warehouseInventoryChangeLog.setChangeReason(warehouseInventoryChangeVo.getChangeReason());

        Integer changeById = userInfoService.getUserId();
        warehouseInventoryChangeLog.setChangeAt(new Date());
        warehouseInventoryChangeLog.setChangedBy(changeById);

        return warehouseInventoryChangeLog;
    }
}
