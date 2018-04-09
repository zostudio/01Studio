package com.zos.coupon.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 浏览器服务端工程
 *
 * @author 01Studio
 */
@RestController
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.zos"})
@EnableJpaRepositories(basePackages ={"com.zos.security.rbac.repository"})
@EntityScan(basePackages ={"com.zos.security.rbac.domain"})
public class CouponBrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponBrowserApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}
}
