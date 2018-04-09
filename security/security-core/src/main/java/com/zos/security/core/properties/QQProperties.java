package com.zos.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQ登录配置项
 *
 * @author 01Studio
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QQProperties extends SocialProperties {
	
	/**
	 * 第三方认证服务 id, 用来决定发起第三方登录的 url, 默认是 qq
	 */
	private String providerId = "qq";
	
}
