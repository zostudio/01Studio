package com.zos.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.zos.security.core.social.qq.api.QQ;
import com.zos.security.core.social.qq.api.QQImpl;

/**
 * OAuth2 流程处理器的提供器,
 * 获取通过第三方服务提供商侧接口, 操作第三方用户信息
 *
 * @author 01Studio
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	// 在第三方服务提供商侧, 注册的应用 appId
	private String appId;
	// 领取授权码的地址
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	// 领取访问令牌的地址
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	/**
	 * 从第三方服务提供商侧获取第三方用户信息的工具类的构造方法, 初始化 Oauth2 协议执行模版
	 *
	 * @param appId 在第三方服务提供商侧, 注册的应用 appId
	 * @param appSecret 在第三方服务提供商侧, 注册的应用 appSecret
	 */
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		// 设置在第三方服务提供商侧, 注册的应用 appId
		this.appId = appId;
	}

	/**
	 * 通过第三方服务提供商操作 QQ 用户信息的接口实现初始化
	 *
	 * @param accessToken 访问令牌
	 * @return 操作 QQ 用户信息接口实现
	 */
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
