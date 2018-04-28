package com.zos.security.rbac.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.redis.dao.RedisCommonDAO;
import com.zos.security.rbac.repository.UserRepository;
import com.zos.security.rbac.service.RbacService;
import com.zos.security.rbac.support.RequestMethod;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RedisCommonDAO<User, Object, Object> userRedisDAO;
	
	@Autowired
	RedisCommonDAO<User.UrlCache, String, RequestMethod> userUrlCacheRedisDAO;
	
	@Autowired
	RedisCommonDAO<User.RoleCache, Long, String> userRoleCacheRedisDAO;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	@Transactional
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		
		Object principal = authentication.getPrincipal();

		boolean hasPermission = true;
		
		if (principal instanceof User) {
			User currentUser = (User) principal;
			/**
			 * 若用户名是 Administrator, 就永远返回 true
			 */
			if (StringUtils.equals(currentUser.getUsername(), "Administrator")) {
				hasPermission = true;
			} else {
				Boolean cacheKey = false;
				Set<User.UrlCache> userUrlCaches = null;
				Set<User.RoleCache> userRoleCaches = null;
				/**
				 * 读取用户所拥有权限的所有角色和接口
				 * 首先判断 Redis 中是否缓存接口权限信息, 如果有缓存数据则缓存中读取数据, 如果没有则从 MySQL获取, 并将接口权限数据缓存到 Redis
				 * 若缓存中没有接口权限数据, 则先将缓存值设置为 null(防止其他服务重复缓存数据), 同时设置过期时间(防止出错), 相当于拿到更新缓存的钥匙
				 * 若其他服务查找接口权限信息是 null, 则直接从 MySQL 中取值
				 * 拿到更新缓存钥匙的服务负责将数据缓存到 Redis
				 */
				String userUrlCachesKey = userUrlCacheRedisDAO.setKey(String.valueOf(currentUser.getId()), null);
				String userRoleCachesKey = userRoleCacheRedisDAO.setKey(String.valueOf(currentUser.getId()), null);
				Long cacheSize = userUrlCacheRedisDAO.opsForSet().size(userUrlCachesKey);
				if (cacheSize.compareTo(0L) == 0) {
					userUrlCacheRedisDAO.opsForSet().add(userUrlCachesKey, new User.UrlCache[]{null});
					userUrlCacheRedisDAO.expire(userUrlCachesKey, 10L, TimeUnit.SECONDS);
					cacheKey = true;
				}
				userUrlCaches = userUrlCacheRedisDAO.opsForSet().members(userUrlCachesKey);
				if (userUrlCaches.contains(null)) {
					currentUser = userRepository.getOne(currentUser.getId());
					userUrlCaches = currentUser.getUrlCaches();
					userRoleCaches = currentUser.getRoleCaches();
				} else {
					userUrlCacheRedisDAO.expire(userUrlCachesKey, 3600L, TimeUnit.SECONDS);
					userUrlCacheRedisDAO.expire(userRoleCachesKey, 3600L, TimeUnit.SECONDS);
				}
				for (User.UrlCache urlCache : userUrlCaches) {
					if (antPathMatcher.match(urlCache.getUrl(), request.getRequestURI()) && request.getMethod().equalsIgnoreCase(urlCache.getMethod().toString())) {
						hasPermission = true;
						break;
					}
				}
				if (cacheKey && CollectionUtils.isNotEmpty(userUrlCaches)) {
					userUrlCacheRedisDAO.opsForSet().add(userUrlCachesKey, userUrlCaches.toArray(new User.UrlCache[]{}));
					userUrlCacheRedisDAO.expire(userUrlCachesKey, 3600L, TimeUnit.SECONDS);
				}
				if (cacheKey && CollectionUtils.isNotEmpty(userRoleCaches)) {
					userRoleCacheRedisDAO.opsForSet().add(userRoleCachesKey, userRoleCaches.toArray(new User.RoleCache[]{}));
					userUrlCacheRedisDAO.expire(userRoleCachesKey, 3600L, TimeUnit.SECONDS);
				}
			}
		}
		return hasPermission;
	}
}
