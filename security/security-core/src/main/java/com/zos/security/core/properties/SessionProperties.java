package com.zos.security.core.properties;

import lombok.Data;

/**
 * Session 相关配置项
 * 
 * @author 01Studio
 *
 */
@Data
public class SessionProperties {
	
	/**
	 * 同一个用户在系统中的最大 Session 数, 默认1, 最多能用多少个浏览器同时登录
	 */
	private int maximumSessions = 1;

	/**
	 * 达到最大 Session 时是否阻止新的登录请求, 默认为false, 不阻止, 因已经达到最大登录数量, 因此新的登录会将老的登录失效掉
	 */
	private boolean maxSessionsPreventsLogin = false;

	/**
	 * Session 失效时跳转的地址
	 */
	private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

}
