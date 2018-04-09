package com.zos.security.core.properties;

import lombok.Data;

/**
 * 浏览器配置项
 * 
 * @author 01Studio
 *
 */
@Data
public class BrowserProperties {
	
	/**
	 * 浏览器 Session 管理配置项
	 */
	private SessionProperties session = new SessionProperties();

	/**
	 * 登录页面, 当引发登录行为的 url 以 html 结尾时, 会跳到这里配置的 url 上
	 */
	private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

	/**
	 * "记住我" 功能的有效时间, 默认1小时
	 */
	private int rememberMeSeconds = 3600;

	/**
	 * 退出成功时跳转的 url, 如果配置了, 则跳到指定的url, 如果没配置, 则返回json数据
	 */
	private String signOutUrl;

	/**
	 * 社交登录, 如果需要用户注册, 跳转的页面
	 */
	private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;

	/**
	 * 登录响应的方式, 默认是json
	 */
	private SignInResponseType signInResponseType = SignInResponseType.JSON;

	/**
	 * 登录成功后跳转的地址, 如果设置了此属性, 则登录成功后总是会跳到这个地址上<br/>
	 * 注意: 只在 signInResponseType 为 REDIRECT 时生效
	 */
	private String singInSuccessUrl;
	
}
