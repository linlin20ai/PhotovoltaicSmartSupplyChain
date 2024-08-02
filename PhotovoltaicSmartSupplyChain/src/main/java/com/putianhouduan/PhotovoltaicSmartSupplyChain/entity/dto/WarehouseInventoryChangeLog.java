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
@TableName("warehouse_inventory_change_log")
public class WarehouseInventoryChangeLog {
    @TableId(type = IdType.AUTO)
    private  Integer changeId;
    private Integer inventoryId;
    private Integer orderId;
    private String changeType;
    private Integer changeQuantity;
    private Integer newQuantity;
    private String changeReason;
    @TableField("changed_at")
    private Date changeAt;
    private Integer changedBy;
}
