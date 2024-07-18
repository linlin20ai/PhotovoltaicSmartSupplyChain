package com.putianhouduan.PhotovoltaicSmartSupplyChain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.WareHouses;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Author: adlx
 * @Description: 仓库管理
 * @DateTime: 2024/7/10 20:25
 *
 **/
@Mapper
public interface WareHousesMapper extends BaseMapper<WareHouses> {
  /*  List<WareHouses> selectAll();

    WareHouses selectBywareId(@Param("id") Long id);*/
}
