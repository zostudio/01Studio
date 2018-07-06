package com.zos.security.oauth.core.social;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.zos.security.oauth.core.AppSecretException;

/**
 * Oauth2 环境下替换 providerSignInUtils, 避免由于没有 session 导致读不到社交用户信息的问题
 * 
 * @author 01Studio
 *
 */
@Component
public class AppSingUpUtils {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	/**
	 * 缓存社交网站用户信息到 redis
	 *
	 * @param request 请求
	 * @param connectionData 社交用户信息
	 */
	public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
		redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
	}

	/**
	 * 将缓存的社交网站用户信息与系统注册用户信息绑定
	 *
	 * @param request 请求
	 * @param userId 用户主键
	 */
	public void doPostSignUp(WebRequest request, String userId) {
		String key = getKey(request);
		if(!redisTemplate.hasKey(key)){
			throw new AppSecretException("无法找到缓存的用户社交账号信息");
		}
		ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
		Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
				.createConnection(connectionData);
		usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
		
		redisTemplate.delete(key);
	}

	/**
	 * 获取redis key
	 *
	 * @param request 请求
	 * @return Redis 缓存主键
	 */
	private String getKey(WebRequest request) {
		String deviceId = request.getHeader("device_id");
		if (StringUtils.isBlank(deviceId)) {
			throw new AppSecretException("device_id 参数不能为空");
		}
		return "zos:security:social.connect." + deviceId;
	}

}
