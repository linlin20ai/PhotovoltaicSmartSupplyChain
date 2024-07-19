package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author 林圣涛
 * 交易订单详细信息
 */
@Getter
@Setter
@Data
@TableName("orders")
public class Order {
    @ApiModelProperty(value = "订单ID")
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    @ApiModelProperty(value = "买方商家ID")
    private Integer buyerId;
    @ApiModelProperty(value = "卖方商家ID")
    private Integer sellerId;
    @ApiModelProperty(value = "交易商品")
    private String item;
    @ApiModelProperty(value = "交易数量")
    private Integer quantity;
    @ApiModelProperty(value = "单价")
    private Double pricePerUnit;
    @ApiModelProperty(value = "订单总金额")
    private Double totalAmount;
    @ApiModelProperty(value = "交易日期")
    private Date transactionDate;
    @ApiModelProperty(value = "交易地点")
    private String location;
    @ApiModelProperty(value = "订单状态：pending,completed,cancelled")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_at")
    private Date updateAt;
}
