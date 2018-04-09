package com.zos.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.zos.security.core.social.weixin.api.Weixin;
import com.zos.security.core.social.weixin.api.WeixinImpl;

/**
 * 
 * 微信的 OAuth2 流程处理器的提供器, 供 spring social 的 connect 体系调用
 * 
 * @author 01Studio
 *
 */
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {
	
	/**
	 * 微信获取授权码的 url
	 */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

	/**
	 * 微信获取 accessToken 的 url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * 初始化 OAuth2 流程处理器的提供器
	 *
	 * @param appId 第三方服务提供商发放给我方的客户端主键 appId
	 * @param appSecret 第三方服务提供商发放给我方的客户端密码 appSecret
	 */
	public WeixinServiceProvider(String appId, String appSecret) {
		super(new WeixinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public Weixin getApi(String accessToken) {
		return new WeixinImpl(accessToken);
	}

}
