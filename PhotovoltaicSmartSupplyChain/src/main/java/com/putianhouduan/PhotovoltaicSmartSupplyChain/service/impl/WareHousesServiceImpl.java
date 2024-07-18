package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dao.WareHousesMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHousesService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.WareHouses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: adlx
 * @Description: TODO
 * @DateTime: 2024/7/10 20:42
 **/
@Service
public class WareHousesServiceImpl extends ServiceImpl<WareHousesMapper,WareHouses> implements WareHousesService {


    @Resource
    WareHousesMapper wareHousesMapper;

    /*@Override
    public List<WareHouses> selectAll() {

        return null;
        *//*wareHousesDao.selectAll();*//*
    }

    @Override
    public WareHouses selectBywareId(Long id) {
        return null;
        *//*wareHousesDao.selectBywareId(id);*//*
    }*/
}
