package com.zos.security.core.social.weixin.connect;

import java.nio.charset.Charset;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 完成微信的 OAuth2 认证流程的模板类, 厂商实现的 OAuth2 每个都不同, spring 默认提供的 OAuth2Template 适应不了, 只能针对每个厂商自己微调
 * 
 * @author 01Studio
 *
 */
@Slf4j
public class WeixinOAuth2Template extends OAuth2Template {

	// 第三方服务提供商发放给我方的客户端主键 appId
	private String clientId;

	// 第三方服务提供商发放给我方的客户端密码 appSecret
	private String clientSecret;

	// 微信获取 accessToken 的 url
	private String accessTokenUrl;

	// 微信操作 refreshToken 的 url
	private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

	public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUrl = accessTokenUrl;
	}

	/**
	 * 微信获取访问授权信息不是走的标准 Oauth2 协议
	 *
	 * @param authorizationCode 授权码
	 * @param redirectUri 跳转的地址
	 * @param parameters 其他参数
	 * @return 访问授权信息
	 */
	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> parameters) {
		
		StringBuilder accessTokenRequestUrl = new StringBuilder(accessTokenUrl);
		
		accessTokenRequestUrl.append("?appid="+clientId);
		accessTokenRequestUrl.append("&secret="+clientSecret);
		accessTokenRequestUrl.append("&code="+authorizationCode);
		accessTokenRequestUrl.append("&grant_type=authorization_code");
		accessTokenRequestUrl.append("&redirect_uri="+redirectUri);
		
		return getAccessToken(accessTokenRequestUrl);
	}
	
	public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
		
		StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);
		
		refreshTokenUrl.append("?appid="+clientId);
		refreshTokenUrl.append("&grant_type=refresh_token");
		refreshTokenUrl.append("&refresh_token="+refreshToken);
		
		return getAccessToken(refreshTokenUrl);
	}

	/**
	 * 领取访问授权信息
	 *
	 * @param accessTokenRequestUrl 领取访问令牌的完整地址
	 * @return 访问授权信息
	 */
	@SuppressWarnings("unchecked")
	private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {
		
		log.info("微信获取 access_token, 请求URL: "+accessTokenRequestUrl.toString());
		
		String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);
		
		log.info("微信获取 access_token, 响应内容: "+response);
		
		Map<String, Object> result = null;
		try {
			result = new ObjectMapper().readValue(response, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 返回错误码时直接返回空
		if(StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))){
			String errcode = MapUtils.getString(result, "errcode");
			String errmsg = MapUtils.getString(result, "errmsg");
			throw new RuntimeException("获取 access_token 失败, errcode: " + errcode + ", errmsg: "+errmsg);
		}
		
		WeixinAccessGrant accessToken = new WeixinAccessGrant(
				MapUtils.getString(result, "access_token"), 
				MapUtils.getString(result, "scope"), 
				MapUtils.getString(result, "refresh_token"), 
				MapUtils.getLong(result, "expires_in"));
		
		accessToken.setOpenId(MapUtils.getString(result, "openid"));
		
		return accessToken;
	}
	
	/**
	 * 构建获取授权码的请求, 也就是引导用户跳转到微信的地址
	 */
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		String url = super.buildAuthenticateUrl(parameters);
		url = url + "&appid="+clientId+"&scope=snsapi_login";
		return url;
	}
	
	public String buildAuthorizeUrl(OAuth2Parameters parameters) {
		return buildAuthenticateUrl(parameters);
	}
	
	/**
	 * 微信返回的 contentType 是html/text, 添加相应的 HttpMessageConverter 来处理
	 */
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
