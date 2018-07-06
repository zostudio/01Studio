package com.zos.security.core.properties;

import lombok.Data;

/**
 * 认证服务注册的第三方应用配置项
 * 
 * @author 01Studio
 *
 */
@Data
public class OAuth2ClientProperties {
	
	/**
	 * 第三方应用appId
	 */
	private String clientId;

	/**
	 * 第三方应用appSecret
	 */
	private String clientSecret;

	/**
	 * 针对此应用发出的 token 的有效时间(默认两个小时)
	 */
	//TODO Token 有效时长
	private int accessTokenValidateSeconds = Integer.MAX_VALUE;
	
}
