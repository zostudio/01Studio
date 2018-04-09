package com.zos.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码存取器
 * 
 * @author 01Studio
 *
 */
public interface ValidateCodeRepository {

	/**
	 * 保存验证码
	 *
	 * @param request 请求
	 * @param code 验证码
	 * @param validateCodeType 验证码类型
	 */
	void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
	/**
	 * 获取验证码
	 *
	 * @param request 请求
	 * @param validateCodeType 验证码类型
	 * @return 验证码
	 */
	ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);
	/**
	 * 移除验证码
	 *
	 * @param request 请求
	 * @param codeType 验证码类型
	 */
	void remove(ServletWebRequest request, ValidateCodeType codeType);

}
