package com.zos.security.core.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * 默认的处理验证用户信息是否存在的 url 前缀
	 */
	String DEFAULT_VALIDATE_EXISTS_URL_PREFIX = "/user/exists";

	/**
	 * 当请求需要身份认证时, 默认跳转的url
	 */
	String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	/**
	 * 默认的账号密码登录请求处理 url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";

	/**
	 * 默认的用户注册请求处理 url
	 */
	String DEFAULT_SIGN_UP_PROCESSING_URL = "/authentication/signup";

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
	 * 默认注册服务
	 */
	String DEFAULT_SIGN_UP_SERVER_URL = "/social/signUp";

	/**
	 * 验证图片验证码时, http 请求中默认的携带图片验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "image_code";

	/**
	 * 验证短信验证码时, http 请求中默认的携带短信验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "sms_code";

	/**
	 * 发送短信验证码或验证短信验证码时, 传递手机号的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

	/**
	 * openid 参数名
	 */
	String DEFAULT_PARAMETER_NAME_OPENID = "open_id";

	/**
	 * providerId 参数名
	 */
	String DEFAULT_PARAMETER_NAME_PROVIDERID = "provider_id";

	/**
	 * Session 失效默认的跳转地址
	 */
	String DEFAULT_SESSION_INVALID_URL = "/default-session-invalid.html";

	/**
	 * 获取第三方用户信息的 url
	 */
	String DEFAULT_SOCIAL_USER_INFO_URL = "/social/user";

	/**
	 * 默认不开放 swagger2 接口测试
	 */
	Boolean DEFAULT_SWAGGER2_ENABLE = false;

	/**
	 * 默认 swagger2 访问地址
 	 */
	List<String> DEFAULT_SWAGGER2_URI = Arrays.asList("/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**");
}
