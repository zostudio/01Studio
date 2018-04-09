package com.zos.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 *
 * @author 01Studio
 *
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 *
	 * @param request 请求
	 * @return 验证码
	 */
	ValidateCode generate(ServletWebRequest request);
	
}
