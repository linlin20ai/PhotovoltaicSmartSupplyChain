package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("merchant_balances")
public class MerchantBalances {
    @TableId
    private Integer balanceId;
    private Integer merchantId;
    private Double balance;
    private Date lastUpdated;
}
