package com.zos.security.core.social.weixin.connect;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.social.oauth2.AccessGrant;

/**
 * 微信的 access_token 信息, 与标准 OAuth2 协议不同, 微信在获取 access_token 时会同时返回 openId, 并没有单独的通过 accessToke 换取 openId 的服务
 * 所以在这里继承了标准 AccessGrant, 添加了 openId 字段, 作为对微信 access_token 信息的封装
 * 
 * @author 01Studio
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WeixinAccessGrant extends AccessGrant {

	private static final long serialVersionUID = -7243374526633186782L;
	
	private String openId;
	
	public WeixinAccessGrant() {
		super("");
	}

	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}
}
