package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.PreOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper.PreOrderMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.OrderService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.PreOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 林圣涛
 */
@Service
public class PreOrderServiceImpl extends ServiceImpl<PreOrderMapper,PreOrder> implements PreOrderService {

    @Resource
    PreOrderMapper preOrderMapper;

    @Resource
    OrderService orderService;
    @Override
    public CommonResult<String> createPreOrder(PreOrder preOrder) {
        preOrder.setCreatedAt(new Date());
        preOrder.setUpdateAt(new Date());
        preOrder.setExpectedTransactionDate(new Date());
        int insert = preOrderMapper.insertPreOrder(preOrder);
        if(insert == 1) {
            return CommonResult.success("交易订单申请成功，请耐心等待交易");
        }else {
            return CommonResult.failed("交易订单申请失败，请稍后重试或者联系管理员");
        }
    }

    @Override
    public void updateStatue(String statue ,Integer preId) {
        UpdateWrapper<PreOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("pre_order_id",preId).set("status",statue);
        preOrderMapper.update(null,updateWrapper);
    }


}
