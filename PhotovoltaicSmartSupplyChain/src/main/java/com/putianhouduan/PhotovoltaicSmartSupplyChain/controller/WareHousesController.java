package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.UserInfoDto;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouses;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WareHouseInventory;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.WarehouseInventoryChangeLog;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.WarehouseInventoryChangeVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.UserInfoService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.WareHouseInventoryChangeLogService;
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

    @Resource
    WareHouseInventoryChangeLogService wareHouseInventoryChangeLogService;

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


    //自定义仓库改变接口
    @RequestMapping(value = "/selfChange",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> addWareHouse(@RequestBody WarehouseInventoryChangeVo warehouseInventoryChangeVo){
        WarehouseInventoryChangeLog warehouseInventoryChangeLog = wareHouseInventoryChangeLogService.VoToDto(warehouseInventoryChangeVo);


        Integer inventoryId = warehouseInventoryChangeVo.getInventoryId();
        WareHouseInventory changWareHouseInventory = wareHouseInventoryService.getById(inventoryId);
        Integer oldQuantity = changWareHouseInventory.getQuantity();
        Integer changeQuantity = warehouseInventoryChangeVo.getChangeQuantity();
        Integer newQuantity = oldQuantity+changeQuantity;
        if(newQuantity < 0){
            return CommonResult.failed("修改的数据有误");
        }
        changWareHouseInventory.setQuantity(newQuantity);

        if(wareHouseInventoryService.changeQuantity(newQuantity,changWareHouseInventory)){
            warehouseInventoryChangeLog.setNewQuantity(newQuantity);
            wareHouseInventoryChangeLogService.save(warehouseInventoryChangeLog);
            return CommonResult.success("数据修改完成");
        }else {
            return CommonResult.failed("有错误，请稍后重试");
        }
    }
}
