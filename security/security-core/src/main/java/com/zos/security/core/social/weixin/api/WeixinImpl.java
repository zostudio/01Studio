package com.zos.security.core.social.weixin.api;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 通过第三方服务提供商操作微信用户信息接口实现, Weixin API 调用模板, scope 为 Request 的 Spring bean, 根据当前用户的 accessToken 创建
 * 
 * @author 01Studio
 *
 */
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 获取用户信息的 url
	 */
	private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";
	
	/**
	 * 通过第三方服务提供商操作微信用户信息接口实现的构造方法
	 *
	 * @param accessToken 访问令牌
	 */
	public WeixinImpl(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
	}

	/**
	 * 默认注册的 StringHttpMessageConverter 字符集为 ISO-8859-1, 而微信返回的是 UTF-8 的, 所以覆盖了原来的方法
	 *
	 * @return 消息转换器
	 */
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}

	/**
	 * 获取微信用户信息
	 *
	 * @param openId 普通用户的标识, 对当前开发者帐号唯一
	 * @return 微信用户信息
	 */
	@Override
	public WeixinUserInfo getUserInfo(String openId) {
		String url = URL_GET_USER_INFO + openId;
		String response = getRestTemplate().getForObject(url, String.class);
		if(StringUtils.contains(response, "errcode")) {
			return null;
		}
		WeixinUserInfo profile = null;
		try {
			profile = objectMapper.readValue(response, WeixinUserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

}
