package com.zos.security.rbac.redis.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.domain.User.RoleCache;
import com.zos.security.rbac.redis.dao.BaseRedisDAO;

import lombok.Getter;

@Getter
@Repository
public class UserRoleCacheRedisDAO extends BaseRedisDAO<User.RoleCache, Long, String> {
	
	private final String KEY_PREFIX_SET = "com:zos:security:rbac:domain.set.user.rolecache.";
	private final String KEY_PREFIX_HASH = "com:zos:security:rbac:domain.hash.user.rolecache.";
	private final String KEY_PREFIX_LIST = "com:zos:security:rbac:domain.list.user.rolecache.";
	private final String KEY_PREFIX_ZSET = "com:zos:security:rbac:domain.zset.user.rolecache.";
	private final String KEY_PREFIX_VALUE = "com:zos:security:rbac:domain.value.user.rolecache.";

	@Override
	public String valueKey(String correct, RoleCache roleCache) {
		return this.KEY_PREFIX_VALUE + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String listKey(String correct, RoleCache roleCache) {
		return this.KEY_PREFIX_LIST + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String setKey(String correct, RoleCache roleCache) {
		return this.KEY_PREFIX_SET + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String zsetKey(String correct, RoleCache roleCache) {
		return this.KEY_PREFIX_ZSET + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}
	
	@Override
	public String hashKey(String correct, RoleCache roleCache) {
		return this.KEY_PREFIX_HASH + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct));
	}

}
