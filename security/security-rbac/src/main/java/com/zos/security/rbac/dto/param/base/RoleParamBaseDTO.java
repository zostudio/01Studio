package com.zos.security.rbac.dto.param.base;


import com.zos.security.rbac.support.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "基本信息查询条件")
public class RoleParamBaseDTO {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色类型
     */
    @ApiModelProperty(value = "角色类型")
    private RoleType type;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String description;
}
