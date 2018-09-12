package com.zos.security.rbac.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公共响应数据传输对象
 *
 * @param <T> 响应数据
 */
@Data
@ApiModel(value = "公共响应数据传输对象")
public class ResponseDTO<T> {

    @ApiModelProperty(value = "响应编码")
    private Integer code;

    @ApiModelProperty(value = "响应消息")
    private String message;

    @ApiModelProperty(value = "响应数据")
    private T data;
}
