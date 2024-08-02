package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouseInventory;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.WareHouseInventoryMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHouseInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 林圣涛
 */
@Service
public class WareHouseInventoryServiceImpl extends ServiceImpl<WareHouseInventoryMapper, WareHouseInventory> implements WareHouseInventoryService {

    @Resource
    WareHouseInventoryMapper wareHouseInventoryMapper;

    @Override
    public List<WareHouseInventory> selectByMerchantId(Integer merchantId) {
        QueryWrapper<WareHouseInventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("merchant_id",merchantId);
        return list(queryWrapper);
    }

    //更新已经存在的仓库库存数据
    @Override
    public Boolean changeQuantity(Integer newQuantity, WareHouseInventory wareHouseInventory) {
        UpdateWrapper<WareHouseInventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("inventory_id",wareHouseInventory.getInventoryId()).set("quantity",newQuantity).set("updated_at",new Date());
        int update = wareHouseInventoryMapper.update(null, updateWrapper);
        return update>0;
    }


}
