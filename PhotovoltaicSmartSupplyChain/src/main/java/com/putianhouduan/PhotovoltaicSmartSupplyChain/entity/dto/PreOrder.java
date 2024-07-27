package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.util.Date;

/**
 * @author 林圣涛
 *申请交易订单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pre_orders")
public class PreOrder {
    @TableId(type = IdType.AUTO)
    private Integer preOrderId;
    private Integer sellerId;
    private String item;
    private Integer quantity;
    private Double pricePerUnit;
    @TableField(exist = true, insertStrategy = FieldStrategy.IGNORED)
    private Double estimatedTotalAmount;
    @TableField("expected_transaction_date")
    private Date expectedTransactionDate;
    private String location;
    private String status;
    private Date createdAt;
    @TableField("updated_at")
    private Date updateAt;
}
