package com.zos.security.rbac.redis.operations.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.zos.security.rbac.redis.operations.ZosValueOperations;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis 字符串操作
 * 
 * @author 01Studio
 *
 */
@Getter
@Setter
@Component
public class ZosValueOperationsImpl implements ZosValueOperations {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valOpsStr;

	@Override
	public void set(String key, String value) {
		valOpsStr.set(key, value);
	}

	@Override
	public String get(String key) {
		return valOpsStr.get(key);
	}

	@Override
	public Integer append(String key, String value) {
		return valOpsStr.append(key, value);
	}

	@Override
	public String get(String key, Long start, Long end) {
		return valOpsStr.get(key, start, end);
	}

	@Override
	public String getAndSet(String key, String value) {
		return valOpsStr.getAndSet(key, value);
	}

	@Override
	public Boolean setBit(String key, Long offset, Boolean value) {
		return valOpsStr.setBit(key, offset, value);
	}

	@Override
	public Boolean getBit(String key, Long offset) {
		return valOpsStr.getBit(key, offset);
	}

	@Override
	public Long size(String key) {
		return valOpsStr.size(key);
	}

	@Override
	public Double increment(String key, Double delta) {
		return valOpsStr.increment(key, delta);
	}

	@Override
	public Long increment(String key, Long delta) {
		return valOpsStr.increment(key, delta);
	}

	@Override
	public Boolean setIfAbsent(String key, String value) {
		return valOpsStr.setIfAbsent(key, value);
	}

	@Override
	public void set(String key, String value, Long timeout, TimeUnit unit) {
		valOpsStr.set(key, value, timeout, unit);
	}

	@Override
	public void set(String key, String value, long offset) {
		valOpsStr.set(key, value, offset);
	}

	@Override
	public void multiSet(Map<String, String> map) {
		valOpsStr.multiSet(map);
	}

	@Override
	public List<String> multiGet(Collection<String> keys) {
		return valOpsStr.multiGet(keys);
	}

	@Override
	public Boolean multiSetIfAbsent(Map<String, String> map) {
		return valOpsStr.multiSetIfAbsent(map);
	}

}
