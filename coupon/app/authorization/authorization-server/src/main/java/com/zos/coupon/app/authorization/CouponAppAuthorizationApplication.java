package com.zos.coupon.app.authorization;

import com.zos.security.oauth.core.social.AppSingUpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.zos"})
public class CouponAppAuthorizationApplication {

	@Autowired
	AppSingUpUtils appSingUpUtils;

	public static void main(String[] args) {
		SpringApplication.run(CouponAppAuthorizationApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello spring security Demo";
	}

	/*@PostMapping("/regist")
	public void regist(@RequestBody SignUpUser user, HttpServletRequest request) {

		// 不管是注册用户还是绑定用户, 都会拿到一个用户唯一标识
		String userId = user.getUsername();
		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
	}*/

}
