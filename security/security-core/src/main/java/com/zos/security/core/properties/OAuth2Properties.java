package com.zos.security.core.properties;

import lombok.Data;

/**
 * 认证服务授权的应用配置项
 *
 * @author 01Studio
 *
 */
@Data
public class OAuth2Properties {
	
	/**
	 * 使用jwt时为token签名的秘钥
	 */
	private String jwtSigningKey = "01Studio";

	/**
	 * tokenStore 存储
	 */
	private String tokenStore = "redis";

	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};
	
}
