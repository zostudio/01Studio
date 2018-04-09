package com.zos.coupon.browser.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;

import com.zos.security.core.validate.code.ValidateCodeGenerator;
import com.zos.security.core.validate.code.image.ImageCode;

/**
 * @author 01Studio
 *
 */
@Slf4j
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	/* (non-Javadoc)
	 * @see com.zos.security.core.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ImageCode generate(ServletWebRequest request) {
		log.warn("更高级的图形验证码生成代码");
		return null;
	}

}
