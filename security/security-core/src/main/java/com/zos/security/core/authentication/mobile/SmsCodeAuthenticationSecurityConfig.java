package com.zos.security.core.authentication.mobile;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

/**
 * 短信登录认证安全配置
 * 
 * @author 01Studio
 *
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // 认证成功处理器
	@Autowired
	private AuthenticationSuccessHandler zosAuthenticationSuccessHandler;

	// 认证失败处理器
	@Autowired
	private AuthenticationFailureHandler zosAuthenticationFailureHandler;

	// 用户信息认证器
	@Autowired
	private UserDetailsService userDetailsService;

	// 持久化令牌组件, 如果是 Oauth2 协议则不会有
	@Autowired(required = false)
	private PersistentTokenRepository persistentTokenRepository;

	/**
	 * 核心模块安全配置, 短信登录
	 *
	 * @param http 安全配置
	 * @throws Exception 异常信息
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/**
		 * 短信登录过滤器配置
		 */
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		// 登录认证管理器
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		// 登录成功处理器
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(zosAuthenticationSuccessHandler);
		// 登录失败处理器
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(zosAuthenticationFailureHandler);
		// "记住我" 功能, 用户登录信息存储
		String key = UUID.randomUUID().toString();
		smsCodeAuthenticationFilter.setRememberMeServices(new PersistentTokenBasedRememberMeServices(key, userDetailsService, persistentTokenRepository));

		// 短信登录验证逻辑
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		// 用户信息认证器(验证用户信息查询服务)
		smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

		// 将短信登录验证过滤器添加到安全过滤器链上
		http.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
