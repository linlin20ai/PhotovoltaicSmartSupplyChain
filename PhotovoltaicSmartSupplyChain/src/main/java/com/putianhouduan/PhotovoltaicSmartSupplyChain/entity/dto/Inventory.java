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
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存ID，主键
     */
    private Integer inventoryId;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 物料ID
     */
    private Integer materialId;

    /**
     * 物料数量
     */
    private Integer quantity;

    /**
     * 最后更新时间
     */
    private Date lastUpdated;

    /**
     * 创建时间
     */
    private Date createdAt;


}
