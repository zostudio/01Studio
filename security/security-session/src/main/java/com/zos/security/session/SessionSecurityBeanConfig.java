package com.zos.security.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.zos.security.session.logout.ZosLogoutSuccessHandler;
import com.zos.security.session.session.ZosExpiredSessionStrategy;
import com.zos.security.session.session.ZosInvalidSessionStrategy;
import com.zos.security.core.properties.SecurityProperties;

/**
 * Session 环境下扩展点配置, 配置在这里的 Bean, 业务系统都可以通过声明同类型或同名的 Bean 来覆盖安全模块默认的配置
 * 
 * @author 01Studio
 *
 */
@Configuration
public class SessionSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * Session 失效时的处理策略配置
	 *
	 * @return 失效时的处理策略
	 */
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new ZosInvalidSessionStrategy(securityProperties);
	}
	
	/**
	 * 并发登录导致前一个 Session 失效时的处理策略配置
	 *
	 * @return 并发登录导致前一个 Session 失效时的处理策略
	 */
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new ZosExpiredSessionStrategy(securityProperties);
	}
	
	/**
	 * 退出时的处理策略配置
	 * 
	 * @return 退出时的处理策略
	 */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new ZosLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
	}
	
}
