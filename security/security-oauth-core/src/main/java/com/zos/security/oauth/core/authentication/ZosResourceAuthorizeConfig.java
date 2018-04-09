package com.zos.security.oauth.core.authentication;

import com.zos.security.core.authorize.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class ZosResourceAuthorizeConfig {

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;


    public void configure(HttpSecurity http) throws Exception {

        authorizeConfigManager.config(http.authorizeRequests());

    }

}
