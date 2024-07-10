package com.putianhouduan.PhotovoltaicSmartSupplyChain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class WareHouses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID，主键
     */
    private Integer warehouseId;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库位置或地址
     */
    private String location;

    /**
     * 仓库描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;


}
