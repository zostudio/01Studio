package com.zos.security.rbac.dto.param.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "基本信息查询条件")
public class TeamParamBaseDTO {

    /**
     * 团队名称
     */
    @ApiModelProperty(value = "团队名称")
    private String name;

    /**
     * 团队描述
     */
    @ApiModelProperty(value = "团队描述")
    private String description;
}
