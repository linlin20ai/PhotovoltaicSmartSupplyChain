package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("warehouses")
public class WareHouses implements Serializable {

    /**
     * 仓库ID，主键
     */
    @TableId(type = IdType.AUTO)
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
