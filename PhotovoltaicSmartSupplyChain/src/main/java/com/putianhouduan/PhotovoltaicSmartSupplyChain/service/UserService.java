package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.ConfirmResetVO;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.EmailRegisterVo;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request.EmailResetVo;

/**
 * @author 林圣涛
 */
public interface UserService extends IService<User> {
    User findUserByNameOrEmail(String text);

    String registerEmailVerifyCode(String type,String email,String ip);


    String registerEmailAccount(EmailRegisterVo emailRegisterVo);


    String resetConfirm(ConfirmResetVO confirmResetVO);

    String resetEmailAccountPassword(EmailResetVo emailResetVo );
}
