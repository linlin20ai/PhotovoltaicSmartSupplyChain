package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request;

import lombok.Data;

/**
 * @author 林圣涛
 */
@Data
public class WarehouseInventoryChangeVo {
    private Integer inventoryId;
    private Integer orderId;
    private String changeType;
    private Integer changeQuantity;
    private String changeReason;
}
