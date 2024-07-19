package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author adlx
 * @since 2024-07-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryChanges implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 变动记录ID，主键
     */
    private Integer changeId;

    /**
     * 库存ID
     */
    private Integer inventoryId;

    /**
     * 变动类型
     */
    private String changeType;

    /**
     * 数量变动
     */
    private Integer quantityChange;

    /**
     * 变动原因
     */
    private String changeReason;

    /**
     * 变动时间
     */
    private Date changeDate;

    /**
     * 创建时间
     */
    private Date createdAt;


}
