package com.zos.security.rbac.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class UserIDAuditorBean implements AuditorAware<Long> {

	@Override
	public Long getCurrentAuditor() {
		SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return -1L;
        }
        if (ctx.getAuthentication() == null) {
            return -1L;
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return -1L;
        }
        Object principal = ctx.getAuthentication().getPrincipal();
        if (principal.getClass().isAssignableFrom(Long.class)) {
            return (Long) principal;
        } else {
            return -1L;
        }
	}

}
