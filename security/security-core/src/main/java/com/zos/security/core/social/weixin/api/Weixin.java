package com.zos.security.core.social.weixin.api;

/**
 * 通过第三方服务提供商操作微信用户信息接口
 * 
 * @author 01Studio
 *
 */
public interface Weixin {

	/**
	 * 获取微信用户信息
	 *
	 * @param openId 用户在第三方服务提供商侧的用户唯一标识
	 * @return 微信用户信息
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}
