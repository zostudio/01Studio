package com.zos.security.core.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 
 * 认证相关的扩展点配置, 配置在这里的 bean, 业务系统都可以通过声明同类型或同名的 bean 来覆盖安全模块默认的配置
 * 
 * @author 01Studio
 *
 */
@Configuration
public class AuthenticationBeanConfig {

	/**
	 * 默认密码处理器
	 *
	 * @return 密码处理器
	 */
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 默认用户信息认证器
	 * 
	 * @return 用户信息认证器(验证用户信息查询服务)
	 */
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public UserDetailsService userDetailsService() {
		return new DefaultUserDetailsService();
	}

	/**
	 * 默认第三方用户信息认证器
	 * 
	 * @return 第三方用户信息认证器(验证第三方用户信息查询服务)
	 */
	@Bean
	@ConditionalOnMissingBean(SocialUserDetailsService.class)
	public SocialUserDetailsService socialUserDetailsService() {
		return new DefaultSocialUserDetailsService();
	}
	
}
