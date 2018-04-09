package com.zos.coupon.app.resource;

import com.zos.security.core.properties.SecurityProperties;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;


@RestController
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.zos"})
@EntityScan(basePackages = {"com.zos.coupon.app.resource.domain"})
public class CouponAppResourceApplication {

	@Autowired
	SecurityProperties securityProperties;

	public static void main(String[] args) {
		SpringApplication.run(CouponAppResourceApplication.class, args);
	}

	@GetMapping("/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) {

		/*String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");

		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                        .parseClaimsJws(token).getBody();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String company = (String) claims.get("company");

		System.out.println(company);*/

		return user;
	}

	@GetMapping("/hi")
	public String getHello() {
		return "hello";
	}
}
