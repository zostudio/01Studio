package com.zos.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.zos.security.core.properties.QQProperties;
import com.zos.security.core.properties.SecurityProperties;
import com.zos.security.core.social.qq.connet.QQConnectionFactory;

/**
 * QQ 登录自动配置类
 *
 * @author 01Studio
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "zos.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	// 安全模块配置项
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 创建连接工厂
	 *
	 * @return 连接工厂
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
