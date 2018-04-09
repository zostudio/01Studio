package com.zos.security.oauth.authentication;

import com.zos.security.oauth.core.authentication.ZosResourceAuthorizeConfig;
import com.zos.security.oauth.core.authentication.ZosResourcesAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ZosResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    ZosResourcesAuthenticationConfig zosResourcesAuthenticationConfig;

    @Autowired
    ZosResourceAuthorizeConfig zosResourceAuthorizeConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        zosResourcesAuthenticationConfig.configure(http);

        zosResourceAuthorizeConfig.configure(http);

    }
}
