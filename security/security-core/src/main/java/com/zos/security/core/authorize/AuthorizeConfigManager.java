package com.zos.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权信息管理器,
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * 
 * @author 01Studio
 *
 */
public interface AuthorizeConfigManager {
	
	/**
	 * 授权配置信息, 添加到授权配置提供器中
	 *
	 * @param config 配置信息
	 */
	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
