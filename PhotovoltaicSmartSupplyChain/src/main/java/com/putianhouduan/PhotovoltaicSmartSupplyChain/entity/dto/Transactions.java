package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 林圣涛
 * 商户余额流动登记表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("transactions")
public class Transactions {
    @TableId(type = IdType.AUTO)
    private Integer transactionId;

    private Integer buyerId;

    private Integer sellerId;

    private  Double amount ;

    private String currency;

    private Date transactionDate;

    private String status;
}
