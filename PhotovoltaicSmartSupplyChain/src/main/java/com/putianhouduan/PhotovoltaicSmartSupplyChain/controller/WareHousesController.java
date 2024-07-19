package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.WareHouses;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHousesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: adlx
 * @Description: 仓库接口
 * @DateTime: 2024/7/10 20:29
 **/

@Controller
@Api(tags = "WareHousesController")
@Tag(name = "WareHousesController", description = "仓库管理")
@RequestMapping("/warehouses")
public class WareHousesController {

    @Resource
    WareHousesService wareHousesService;

    @ApiOperation("获取全部仓库信息")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<WareHouses>> selectAll() {
        List<WareHouses> wareHouseslist = wareHousesService.list();
        return CommonResult.success(wareHouseslist);
    }

    @ApiOperation("根据id查看仓库详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<WareHouses> selectBywareId(@PathVariable Long id) {
        WareHouses wareHouses = wareHousesService.getById(id);
        return CommonResult.success(wareHouses);
    }
}
