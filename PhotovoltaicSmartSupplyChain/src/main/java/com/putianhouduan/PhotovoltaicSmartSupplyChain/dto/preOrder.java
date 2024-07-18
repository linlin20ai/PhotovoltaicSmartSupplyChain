package com.putianhouduan.PhotovoltaicSmartSupplyChain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author 林圣涛
 *申请交易订单表
 */
@Getter
@Setter
@Data
@TableName("pre_orders")
public class preOrder {
    @TableId(type = IdType.AUTO)
    private Integer preOrderId;
    @TableField("seller_id")
    private Integer sellerId;
    @TableField("item")
    private String item;
    private Integer quantity;
    private Double pricePerUnit;
    @TableField(exist = false)
    private Double estimatedTotalAmount;
    @TableField("expected_transaction_date")
    private Date expectedTransactionDate;
    private String location;
    private String status;
    private Date createdAt;
    @TableField("updated_at")
    private Date updateAt;
}
