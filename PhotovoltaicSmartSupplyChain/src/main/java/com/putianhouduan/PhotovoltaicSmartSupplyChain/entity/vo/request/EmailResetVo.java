package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * @author 林圣涛
 */
@Data
public class EmailResetVo {
    @Email
    String email;
    @Length(min=6,max=6)
    String code;
    @Length(min=6,max=20)
    String password;

}
