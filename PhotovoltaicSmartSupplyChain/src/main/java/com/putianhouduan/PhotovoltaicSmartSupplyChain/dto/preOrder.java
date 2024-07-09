package com.putianhouduan.PhotovoltaicSmartSupplyChain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author 林圣涛
 *申请交易订单表
 */
@Getter
@Setter
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
