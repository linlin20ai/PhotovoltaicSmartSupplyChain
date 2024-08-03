package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("notifications")
public class Notifications {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer merchantId;
    private String message;
    private Integer isRead;
    private Date createdAt;
}
