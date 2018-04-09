package com.zos.security.core.social.qq.connet;

import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 完成 OAuth2 认证流程的模板类
 * 执行 Oauth2 协议中授权码模式的前五步操作的标准模版工具类的实现,
 * 如果是用的第三方服务提供商的 SDK, 则这些操作都在他们的 SDK 中
 *
 * @author 01Studio
 *
 */
@Slf4j
public class QQOAuth2Template extends OAuth2Template {

	/**
	 * 标准模版工具实现类的构造方法, 初始化执行环境
	 * 配置前置条件
	 *
	 * @param clientId 第三方服务提供商发放给我方的客户端主键 appId
	 * @param clientSecret 第三方服务提供商发放给我方的客户端密码 appSecret
	 * @param authorizeUrl 授权地址, 取得授权码
	 * @param accessTokenUrl 领取访问令牌的地址
	 */
	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		// 携带我方在第三方服务提供商侧, 申请的接入信息参数标识, 申请令牌需要的 client_id(appId) 和 client_secret(appSecret)
		setUseParametersForClientAuthentication(true);
	}

	/**
	 * 获取访问授权信息
	 *
	 * @param accessTokenUrl 领取访问令牌的地址
	 * @param parameters 授权码等参数信息
	 * @return 访问授权信息
	 */
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		// 领取访问令牌相关信息
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		
		log.info("领取 accessToke 的响应: " + responseStr);
		
		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		
		String accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
		String refreshToken = StringUtils.substringAfterLast(items[2], "=");
		
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}

	/**
	 * 创建 RESTful 请求的工具模版
	 *
	 * @return RESTful 请求的工具模版
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		// 针对 QQ 的响应数据格式, 添加消息转换器
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
