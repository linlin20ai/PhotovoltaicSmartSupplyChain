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
public class Materials implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 材料ID，主键
     */
    private Integer materialId;

    /**
     * 材料名称
     */
    private String name;

    /**
     * 材料描述
     */
    private String description;

    /**
     * 材料单位
     */
    private String unit;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;


}
