package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * @author 林圣涛
 */
@Data
@AllArgsConstructor
public class ConfirmResetVO {
    @Email
    String email;
    @Length(max=6,min=6)
    String code;
}
