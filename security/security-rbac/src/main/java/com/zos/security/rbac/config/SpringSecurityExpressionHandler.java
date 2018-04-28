package com.zos.security.rbac.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

@Configuration
public class SpringSecurityExpressionHandler extends OAuth2WebSecurityExpressionHandler {

	@Bean
	public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
		OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler = new OAuth2WebSecurityExpressionHandler();
		oAuth2WebSecurityExpressionHandler.setApplicationContext(applicationContext);
		return oAuth2WebSecurityExpressionHandler;
	}
}
