package com.zos.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 微信登录配置项
 * 
 * @author 01Studio
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WeixinProperties extends SocialProperties {
	
	/**
	 * 第三方认证服务 id, 用来决定发起第三方登录的 ur, 默认是 weixin
	 */
	private String providerId = "weixin";

}
