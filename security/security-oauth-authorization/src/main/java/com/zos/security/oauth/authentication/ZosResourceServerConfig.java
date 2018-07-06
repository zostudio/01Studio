package com.zos.security.oauth.authentication;

import com.zos.security.oauth.core.authentication.ZosResourceAuthorizeConfig;
import com.zos.security.oauth.core.authentication.ZosResourcesAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ZosResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	TokenStore tokenStore;
	
    @Autowired
    ZosResourcesAuthenticationConfig zosResourcesAuthenticationConfig;
	
	@Autowired
	OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler;

    @Autowired
    ZosResourceAuthorizeConfig zosResourceAuthorizeConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        zosResourcesAuthenticationConfig.configure(http);

        zosResourceAuthorizeConfig.configure(http);

    }

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("oauth2-resource").tokenStore(tokenStore).expressionHandler(oAuth2WebSecurityExpressionHandler);
	}
}
