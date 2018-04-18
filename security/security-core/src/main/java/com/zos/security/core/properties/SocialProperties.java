package com.zos.security.core.properties;

import lombok.Data;

/**
 * 社交登录配置项
 *
 * @author 01Studio
 *
 */
@Data
public class SocialProperties {
	
	/**
	 * 社交登录功能拦截的 url
	 */
	private String filterProcessesUrl = "/auth";
	
	/**
	 * 社交登录注册 url
	 */
	private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;

	/**
	 * QQ
	 */
	private QQProperties qq = new QQProperties();

	/**
	 * 微信
	 */
	private WeixinProperties weixin = new WeixinProperties();

}
