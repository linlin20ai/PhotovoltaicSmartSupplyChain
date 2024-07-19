package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
public class AuthorzeVo {
    String name;
    String role;
    String token;
    Date expire;
}
