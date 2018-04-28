package com.zos.security.rbac.redis.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.redis.dao.BaseRedisDAO;

import lombok.Getter;

@Getter
@Repository
public class UserRedisDAO extends BaseRedisDAO<User, Object, Object> {
	
	private final String KEY_PREFIX_SET = "com:zos:security:rbac:domain.set.user.";
	private final String KEY_PREFIX_HASH = "com:zos:security:rbac:domain.hash.user.";
	private final String KEY_PREFIX_LIST = "com:zos:security:rbac:domain.list.user.";
	private final String KEY_PREFIX_ZSET = "com:zos:security:rbac:domain.zset.user.";
	private final String KEY_PREFIX_VALUE = "com:zos:security:rbac:domain.value.user.";
	
	@Override
	public String valueKey(String correct, User user) {
		return this.KEY_PREFIX_VALUE + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct)) + user.getId();
	}
	
	@Override
	public String listKey(String correct, User user) {
		return this.KEY_PREFIX_LIST + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct)) + user.getId();
	}
	
	@Override
	public String setKey(String correct, User user) {
		return this.KEY_PREFIX_SET + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct)) + user.getId();
	}
	
	@Override
	public String zsetKey(String correct, User user) {
		return this.KEY_PREFIX_ZSET + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct)) + user.getId();
	}
	
	@Override
	public String hashKey(String correct, User user) {
		return this.KEY_PREFIX_HASH + (StringUtils.isBlank(correct) ? "" : StringUtils.trim(correct)) + user.getId();
	}
}
