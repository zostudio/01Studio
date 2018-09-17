package com.zos.security.rbac.dto.param.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@ApiModel(value = "表间关系")
public class RelationsAddDTO {

    @ApiModelProperty(value = "关联方主键列表")
    @NotNull(message = "关联方主键列表不能为空")
    private Set<String> relationsIds;
}
