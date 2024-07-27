package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    @ApiModelProperty(value = "订单ID")
    private Integer orderId;
    @ApiModelProperty(value = "买方商家")
    private String buyerName;
    @ApiModelProperty(value = "卖方商家")
    private String sellerName;
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
