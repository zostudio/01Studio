package com.zos.security.rbac.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.zos.security.rbac.domain"})
@EnableJpaRepositories(basePackages ={"com.zos.security.rbac.repository"})
public class SpringSecurityRbacScanConfig {

}
