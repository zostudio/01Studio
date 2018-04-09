package com.zos.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.zos.security.core.properties.SecurityConstants;

/**
 * 表单登录认证配置
 * 
 * @author 01Studio
 *
 */
@Component
public class FormAuthenticationConfig {

	// 认证成功处理器
	@Autowired
	protected AuthenticationSuccessHandler zosAuthenticationSuccessHandler;

	// 认证失败处理器
	@Autowired
	protected AuthenticationFailureHandler zosAuthenticationFailureHandler;

	/**
	 * 表单登录认证配置
	 *
	 * @param http 认证安全配置
	 * @throws Exception 异常信息
	 */
	public void configure(HttpSecurity http) throws Exception {
		// 表单登录
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 默认登录页面
			.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM) // 默认登录处理 url
			.successHandler(zosAuthenticationSuccessHandler) // 认证成功处理器
			.failureHandler(zosAuthenticationFailureHandler); // 认证失败处理器
	}
	
}
