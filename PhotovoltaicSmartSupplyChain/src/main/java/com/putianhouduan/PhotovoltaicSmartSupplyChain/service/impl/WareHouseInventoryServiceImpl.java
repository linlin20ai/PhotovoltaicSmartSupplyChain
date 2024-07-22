package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouseInventory;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.WareHouseInventoryMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHouseInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林圣涛
 */
@Service
public class WareHouseInventoryServiceImpl extends ServiceImpl<WareHouseInventoryMapper, WareHouseInventory> implements WareHouseInventoryService {
    @Override
    public List<WareHouseInventory> selectByMerchantId(Integer merchantId) {
        QueryWrapper<WareHouseInventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("merchant_id",merchantId);
        return list(queryWrapper);
    }
}
