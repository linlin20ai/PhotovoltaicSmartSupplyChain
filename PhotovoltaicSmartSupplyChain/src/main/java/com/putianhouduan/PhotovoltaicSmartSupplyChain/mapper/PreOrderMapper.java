package com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 林圣涛
 */
@Mapper
public interface PreOrderMapper  extends BaseMapper<PreOrder> {

    @Insert("INSERT INTO pre_orders (seller_id, item, quantity, price_per_unit, expected_transaction_date,location, status) VALUES (#{sellerId}, #{item}, #{quantity}, #{pricePerUnit},#{expectedTransactionDate} ,#{location}, #{status})")
    int insertPreOrder(PreOrder preOrder);
}
