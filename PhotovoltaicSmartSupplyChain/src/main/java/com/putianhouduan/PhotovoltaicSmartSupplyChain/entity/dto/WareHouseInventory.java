package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("warehouse_inventory")
public class WareHouseInventory {
    @TableId(type = IdType.AUTO)
    Integer inventoryId;
    Integer materialId;
    @TableField("material_name")
    String materIalName;
    String unit;
    Integer quantity;
    String location;
    Integer merchantId;
    @TableField("created_at")
    Date createdAt;
    Date updatedAt;
}
