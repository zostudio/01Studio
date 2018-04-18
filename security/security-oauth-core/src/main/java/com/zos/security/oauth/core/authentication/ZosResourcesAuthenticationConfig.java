package com.zos.security.oauth.core.authentication;

import com.zos.security.core.authentication.FormAuthenticationConfig;
import com.zos.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zos.security.core.properties.SecurityConstants;
import com.zos.security.core.validate.code.ValidateCodeSecurityConfig;
import com.zos.security.oauth.core.authentication.openid.OpenIdAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ZosResourcesAuthenticationConfig {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer zosSocialSecurityConfig;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(zosSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
    			.authorizeRequests().antMatchers(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL).permitAll().and()
                .csrf().disable();
    }
}
