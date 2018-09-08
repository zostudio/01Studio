package com.zos.security.rbac.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
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
            log.info("operator: {}", ToStringBuilder.reflectionToString(principal, ToStringStyle.MULTI_LINE_STYLE));
            return (Long) principal;
        } else {
            return -1L;
        }
	}

}
