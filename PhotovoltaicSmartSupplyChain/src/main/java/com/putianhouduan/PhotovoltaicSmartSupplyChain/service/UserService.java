package com.putianhouduan.PhotovoltaicSmartSupplyChain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto.User;

/**
 * @author 林圣涛
 */
public interface UserService extends IService<User> {
    User findUserByNameOrEmail(String text);

    String registerEmailVerifyCode(String type,String email,String ip);
}
