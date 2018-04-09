package com.zos.security.core.properties;

/**
 * 核心模块系统常量
 *
 * @author 01Studio
 *
 */
public interface SecurityConstants {
	
	/**
	 * 默认的处理验证码的 url 前缀
	 */
	String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

	/**
	 * 当请求需要身份认证时, 默认跳转的url
	 */
	String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	/**
	 * 默认的用户名密码登录请求处理 url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";

	/**
	 * 默认的手机验证码登录请求处理 url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";

	/**
	 * 默认的 OPENID 登录请求处理 url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";

	/**
	 * 默认登录页面
	 */
	String DEFAULT_SIGN_IN_PAGE_URL = "/default-signIn.html";

	/**
	 * 默认注册页面
	 */
	String DEFAULT_SIGN_UP_PAGE_URL = "/default-signUp.html";

	/**
	 * 验证图片验证码时, http 请求中默认的携带图片验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

	/**
	 * 验证短信验证码时, http 请求中默认的携带短信验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

	/**
	 * 发送短信验证码或验证短信验证码时, 传递手机号的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

	/**
	 * openid 参数名
	 */
	String DEFAULT_PARAMETER_NAME_OPENID = "openId";

	/**
	 * providerId 参数名
	 */
	String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";

	/**
	 * Session 失效默认的跳转地址
	 */
	String DEFAULT_SESSION_INVALID_URL = "/default-session-invalid.html";

	/**
	 * 获取第三方用户信息的 url
	 */
	String DEFAULT_SOCIAL_USER_INFO_URL = "/social/user";
}
