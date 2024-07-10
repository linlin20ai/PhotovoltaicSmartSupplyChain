package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.WareHouses;

import java.util.List;

/**
 * @Author: adlx
 * @Description: TODO
 * @DateTime: 2024/7/10 20:42
 **/
public interface WareHousesService {
    List<WareHouses> selectAll();

    WareHouses selectBywareId(Long id);
}
