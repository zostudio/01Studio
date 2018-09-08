package com.zos.security.core.properties;

import lombok.Data;

import java.util.List;

/**
 * Swagger2 配置
 *
 * @author 01Studio
 */
@Data
public class Swagger2Properties {

    /**
     * 是否开放 Swagger2
     */
    private boolean enable = SecurityConstants.DEFAULT_SWAGGER2_ENABLE;

    /**
     * 令牌
     */
    private String token = "";

    /**
     * 机器码
     */
    private String deviceId = "";

    /**
     * uris
     */
    private List<String> uris = SecurityConstants.DEFAULT_SWAGGER2_URI;

}
