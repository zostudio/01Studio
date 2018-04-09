package com.zos.security.core.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 默认的授权配置管理器
 * 
 * @author 01Studio
 *
 */
@Component
public class ZosAuthorizeConfigManager implements AuthorizeConfigManager {

	/**
	 * 授权配置提供器列表
	 */
	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders;

	/**
	 * 授权配置信息, 添加到授权配置提供器中
	 *
	 * @param config 配置信息
	 */
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		boolean existAnyRequestConfig = false;
		String existAnyRequestConfigName = null;
		
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
			if (existAnyRequestConfig && currentIsAnyRequestConfig) {
				throw new RuntimeException("重复的anyRequest配置: " + existAnyRequestConfigName + ", "
						+ authorizeConfigProvider.getClass().getSimpleName());
			} else if (currentIsAnyRequestConfig) {
				existAnyRequestConfig = true;
				existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
			}
		}
		
		if(!existAnyRequestConfig){
			config.anyRequest().authenticated();
		}
	}

}
