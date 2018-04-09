package com.zos.security.core.validate.code.sms;

/**
 * 短信验证码发送器
 *
 * @author 01Studio
 *
 */
public interface SmsCodeSender {
	
	/**
	 * 发送短信验证码
	 *
	 * @param mobile 手机号码
	 * @param code 验证码
	 */
	void send(String mobile, String code);

}
