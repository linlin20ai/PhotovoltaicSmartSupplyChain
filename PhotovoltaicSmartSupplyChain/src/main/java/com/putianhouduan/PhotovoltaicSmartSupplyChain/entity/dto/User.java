package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
@NoArgsConstructor
@TableName("users")
@AllArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    Integer userId;

    Integer merchantId;
    String username;
    String password;
    Integer role;
    Integer enabled;
    String email;


}
