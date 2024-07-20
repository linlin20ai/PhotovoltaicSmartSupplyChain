package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import lombok.*;

import java.util.Date;

/**
 * @author 林圣涛
 *申请交易订单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class preOrder {
    private int preOrderId;
    private int sellerId;
    private String item;
    private int quantity;
    private double pricePerUnit;
    private double estimatedTotalAmount;
    private String location;
    private String status;
    private Date createdAt;
    private Date updateAt;
}
