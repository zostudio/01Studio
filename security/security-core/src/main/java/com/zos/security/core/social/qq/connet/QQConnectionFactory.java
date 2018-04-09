package com.zos.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.zos.security.core.social.qq.api.QQ;

/**
 * 第三方链接工厂
 *
 * @author 01Studio
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * 第三方链接工厂构造方法
	 *
	 * @param providerId 第三方服务提供商, 在我方的唯一标识
	 * @param appId 在第三方服务提供商侧, 注册的应用 appId
	 * @param appSecret 在第三方服务提供商侧, 注册的应用 appSecret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
