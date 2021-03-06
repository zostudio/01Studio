package com.zos.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全模块配置项
 *
 * @author 01Studio
 *
 */
@Data
@ConfigurationProperties(prefix = "zos.security")
public class SecurityProperties {
	
	/**
	 * 浏览器配置项
	 */
	private BrowserProperties browser = new BrowserProperties();

	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

	/**
	 * 社交登录配置
	 */
	private SocialProperties social = new SocialProperties();

	/**
	 * OAuth2 认证服务器配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();

	/**
	 * Swagger2 配置
	 */
	private Swagger2Properties swagger = new Swagger2Properties();
}

