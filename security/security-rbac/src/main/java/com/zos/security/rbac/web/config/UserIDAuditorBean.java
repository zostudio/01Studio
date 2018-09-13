package com.zos.security.rbac.web.config;

import com.zos.security.rbac.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@Configuration
public class UserIDAuditorBean implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return "88888888888888888888888888888888";
        }
        if (ctx.getAuthentication() == null) {
            return "88888888888888888888888888888888";
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return "88888888888888888888888888888888";
        }
        if (ctx.getAuthentication().getPrincipal() instanceof User) {
            User principal = (User)ctx.getAuthentication().getPrincipal();
            if (principal.getId() != null && principal.getId().getClass().isAssignableFrom(Long.class)) {
                log.info("operator: {}", ToStringBuilder.reflectionToString(principal, ToStringStyle.MULTI_LINE_STYLE));
                return principal.getId();
            } else {
                return "88888888888888888888888888888888";
            }
        } else {
            return "88888888888888888888888888888888";
        }
	}

}
