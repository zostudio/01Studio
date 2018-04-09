package com.zos.security.core.social.support;

import lombok.Data;

/**
 * 社交帐号用户信息
 *
 * @author 01Studio
 *
 */
@Data
public class SocialUserInfo {

	// 第三方服务提供商, 在我方的唯一标识
	private String providerId;

	// 用户在第三方服务提供商侧的用户唯一标识
	private String providerUserId;

	// 名称
	private String nickname;

	// 头像
	private String headimg;

}
