package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHousesService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dao.WareHousesDao;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.WareHouses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: adlx
 * @Description: TODO
 * @DateTime: 2024/7/10 20:42
 **/
@Service
public class WareHousesServiceImpl implements WareHousesService {

    @Autowired
    private WareHousesDao wareHousesDao;

    @Override
    public List<WareHouses> selectAll() {
        return wareHousesDao.selectAll();
    }

    @Override
    public WareHouses selectBywareId(Long id) {
        return wareHousesDao.selectBywareId(id);
    }
}
