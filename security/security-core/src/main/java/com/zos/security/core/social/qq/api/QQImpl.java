package com.zos.security.core.social.qq.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 通过第三方服务提供商操作 QQ 用户信息接口实现
 *
 * @author 01Studio
 *
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	// 查询 QQ 用户在第三方服务提供商侧的用户唯一标识 openId 的地址
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	// 查询 QQ 用户信息的地址
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	// 在第三方服务提供商侧, 注册的应用 appId
	private String appId;

	// QQ 用户在第三方服务提供商侧的用户唯一标识
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 通过第三方服务提供商操作 QQ 用户信息接口实现的构造方法,
	 * 主要作用是初始化通过第三方服务提供商操作 QQ 用户信息的必要条件
	 *
	 * @param accessToken 访问令牌
	 * @param appId 在第三方服务提供商侧, 注册的应用 appId
	 */
	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		// 设置在第三方服务提供商侧, 注册的应用 appId
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		
		log.info("查询 QQ 用户在第三方服务提供商侧的用户唯一标识结果: " + result);
		// 设置QQ 用户在第三方服务提供商侧的用户唯一标识
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}

	/**
	 * 查询 QQ 用户信息
	 *
	 * @return QQ 用户信息
	 */
	@Override
	public QQUserInfo getUserInfo() {
		
		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);

		log.info("查询 QQ 用户在第三方服务提供商侧的用户信息结果: " + result);
		
		QQUserInfo userInfo = null;
		try {
			userInfo = objectMapper.readValue(result, QQUserInfo.class);
			// 将 QQ 用户在第三方服务提供商侧的用户唯一标识, 存入 QQ 用户信息中
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}

}
