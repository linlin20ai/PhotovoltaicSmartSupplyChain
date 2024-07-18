package com.putianhouduan.PhotovoltaicSmartSupplyChain.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dao.preOrderMapper;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.preOrder;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.preOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 林圣涛
 */
@Service
public class preOrderServiceImpl extends ServiceImpl<preOrderMapper, preOrder> implements preOrderService {


    @Resource
    preOrderMapper preOrderMapper;

}
