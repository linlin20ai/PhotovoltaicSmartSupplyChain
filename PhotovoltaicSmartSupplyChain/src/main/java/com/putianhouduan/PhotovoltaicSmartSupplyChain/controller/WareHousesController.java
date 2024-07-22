package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.UserInfoDto;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouses;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouseInventory;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserInfoService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHouseInventoryService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHousesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    @Resource
    UserInfoService userInfoService;

    @Resource
    WareHouseInventoryService wareHouseInventoryService;

    @ApiOperation("获取全部仓库信息")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<WareHouses>> selectAll() {
         return Optional.ofNullable(wareHousesService.list())
                 .map(CommonResult::success)
                .orElseGet(()->CommonResult.failed("系统出了点问题，请联系管理员"));
    }

    @ApiOperation("根据id查看仓库详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<WareHouses> selectBywareId(@PathVariable Long id) {
        return Optional.ofNullable(wareHousesService.getById(id))
                .map(CommonResult::success)
                .orElseGet(()->CommonResult.failed("请仔细检查输入的id是否正确或者联系管理员"));
    }

    @ApiOperation("根据商家查看商家仓库情况")
    @RequestMapping(value = "/warehouse",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<WareHouseInventory>> selectPersonWareHouse( ){
        return Optional.ofNullable(wareHouseInventoryService.selectByMerchantId(userInfoService.getMerchantId()))
                .map(CommonResult::success)
                .orElseGet(()->CommonResult.failed("请仔细检查输入的id是否正确或者请立马联系管理员"));
    }
}
