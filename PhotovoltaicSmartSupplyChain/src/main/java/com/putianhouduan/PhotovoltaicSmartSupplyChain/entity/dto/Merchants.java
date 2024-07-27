package com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author 林圣涛
 * 商家（公司）信息参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("merchants")
public class Merchants {
    @ApiModelProperty(value = "商家ID")
    @TableId(type= IdType.AUTO)
    @TableField("merchant_id")
    private Integer merchantId;
    @ApiModelProperty(value = "商家名字")
    private String name;
    @ApiModelProperty(value = "商家描述")
    private String description;
    @ApiModelProperty(value = "商家的联系人姓名")
    private String contactName;
    @ApiModelProperty(value = "商家的联系人电话")
    private String contactPhone;
    @ApiModelProperty(value = "商家邮箱")
    private String email;
    @ApiModelProperty(value = "商家地址")
    private String address;
    @ApiModelProperty(value = "商家所在城市")
    private String city;
    @ApiModelProperty(value = "商家所在的省")
    private String state;
    @ApiModelProperty(value = "商家的邮政编码")
    private String zipCode;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "最后更新时间")
    @TableField("updated_at")
    private Date updateAt;
}
