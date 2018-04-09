package com.zos.security.core.social;

import org.springframework.social.connect.Connection;

import com.zos.security.core.social.support.SocialUserInfo;

/**
 * 社交控制器
 *
 * @author 01Studio
 *
 */
public abstract class SocialController {

	/**
	 * 根据 Connection 信息构建 SocialUserInfo
	 *
	 * @param connection 社交帐号用户信息
	 * @return 自定义社交帐号用户信息
	 */
	protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
		SocialUserInfo userInfo = new SocialUserInfo();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}
	
}
