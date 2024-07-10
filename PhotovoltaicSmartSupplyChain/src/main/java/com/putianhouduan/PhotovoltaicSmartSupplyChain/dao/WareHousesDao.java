package com.putianhouduan.PhotovoltaicSmartSupplyChain.dao;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.WareHouses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: adlx
 * @Description: 仓库管理
 * @DateTime: 2024/7/10 20:25
 **/
public interface WareHousesDao {
    List<WareHouses> selectAll();

    WareHouses selectBywareId(@Param("id") Long id);
}
