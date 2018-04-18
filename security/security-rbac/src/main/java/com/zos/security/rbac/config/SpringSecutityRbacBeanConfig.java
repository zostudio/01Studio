package com.zos.security.rbac.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpringSecutityRbacBeanConfig {
	
	@Autowired
    private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
        log.info("Init JPAQueryFactory");
		return new JPAQueryFactory(entityManager);
	}
}
