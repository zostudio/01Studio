package com.zos.security.rbac.redis.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.domain.User.UrlCache;
import com.zos.security.rbac.redis.dao.BaseRedisDAO;
import com.zos.security.rbac.support.RequestMethod;

import lombok.Getter;

@Getter
@Repository
public class UserUrlCacheRedisDAO extends BaseRedisDAO<User.UrlCache, String, RequestMethod> {
	
	private final String KEY_PREFIX_SET = "com:zos:security:rbac:domain.set.user.urlcache.";
	private final String KEY_PREFIX_HASH = "com:zos:security:rbac:domain.hash.user.urlcache.";
	private final String KEY_PREFIX_LIST = "com:zos:security:rbac:domain.list.user.urlcache.";
	private final String KEY_PREFIX_ZSET = "com:zos:security:rbac:domain.zset.user.urlcache.";
	private final String KEY_PREFIX_VALUE = "com:zos:security:rbac:domain.value.user.urlcache.";

	@Override
	public String valueKey(String correct, UrlCache urlCache) {
		return this.KEY_PREFIX_VALUE + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String listKey(String correct, UrlCache urlCache) {
		return this.KEY_PREFIX_LIST + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String setKey(String correct, UrlCache urlCache) {
		return this.KEY_PREFIX_SET + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String zsetKey(String correct, UrlCache urlCache) {
		return this.KEY_PREFIX_ZSET + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String hashKey(String correct, UrlCache urlCache) {
		return this.KEY_PREFIX_HASH + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}

}
