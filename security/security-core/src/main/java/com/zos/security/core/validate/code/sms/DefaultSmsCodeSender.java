package com.zos.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 * 
 * @author 01Studio
 *
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

	/* (non-Javadoc)
	 * @see com.zos.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String mobile, String code) {
		log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		log.info("向手机: "+mobile+", 发送短信验证码: "+code);
	}

}
