package com.zos.security.rbac.dto.param.info;

import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.support.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value = "账号详细信息")
public class UserParamInfoDTO extends UserParamBaseDTO {

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 证件
     */
    @ApiModelProperty(value = "证件")
    private String identity;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private String dateOfBirth;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Gender gender;
}
