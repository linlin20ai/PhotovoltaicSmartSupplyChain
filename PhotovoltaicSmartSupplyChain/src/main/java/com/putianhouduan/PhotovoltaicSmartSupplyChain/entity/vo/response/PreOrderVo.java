package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.response;


import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Merchants;
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
public class PreOrderVo {
    private Integer preOrderId;
    private String sellerName;
    private String item;
    private Integer quantity;
    private Double pricePerUnit;
    private Double estimatedTotalAmount;
    private String location;
    private String status;
    private Date transactionDate;


}
