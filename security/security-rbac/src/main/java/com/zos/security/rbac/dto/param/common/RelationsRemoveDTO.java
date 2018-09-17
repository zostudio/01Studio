package com.zos.security.rbac.dto.param.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "表间关系")
public class RelationsRemoveDTO {

    @ApiModelProperty(value = "关联方主键列表")
    private Set<String> relationsIds;
}
