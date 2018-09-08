package com.zos.security.core.authorize;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.zos.security.core.properties.SecurityConstants;
import com.zos.security.core.properties.SecurityProperties;

/**
 * 核心模块的授权配置提供器, 安全模块涉及的 url 的授权配置在这里
 * 
 * @author 01Studio
 *
 */
@Slf4j
@Component
@Order(Integer.MIN_VALUE)
public class ZosAuthorizeConfigProvider implements AuthorizeConfigProvider {

	// 安全配置
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 用授权表达式配置授权信息
	 *
	 * @param config 配置信息
	 * @return 布尔型, 标识是否有针对anyRequest的配置
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
				SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
				SecurityConstants.DEFAULT_VALIDATE_EXISTS_URL_PREFIX + "/*",
				securityProperties.getBrowser().getSignInPage(), 
				securityProperties.getBrowser().getSignUpUrl(),
				securityProperties.getBrowser().getSession().getSessionInvalidUrl())
				.permitAll();

		log.info("swagger.enable: {}", securityProperties.getSwagger().isEnable());
		log.info("swagger.uris: {}", securityProperties.getSwagger().getUris());
		if (securityProperties.getSwagger().isEnable() && CollectionUtils.isNotEmpty(securityProperties.getSwagger().getUris())) {
			for (String uri : securityProperties.getSwagger().getUris()) {
				config.antMatchers(uri).permitAll();
			}
		}

		if (StringUtils.isNotBlank(securityProperties.getBrowser().getSignOutUrl())) {
			config.antMatchers(securityProperties.getBrowser().getSignOutUrl()).permitAll();
		}
		return false;
	}

}
