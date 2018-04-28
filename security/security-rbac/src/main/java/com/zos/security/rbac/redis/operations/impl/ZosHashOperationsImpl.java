package com.zos.security.rbac.redis.operations.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import com.zos.security.rbac.redis.operations.ZosHashOperations;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis Hash 操作
 * 
 * @author 01Studio
 *
 * @param <HK> 哈希键
 * @param <HV> 哈希值
 */
@Getter
@Setter
@Component
public class ZosHashOperationsImpl<HK, HV> implements ZosHashOperations<HK, HV> {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private HashOperations<String, HK, HV> hashOps;

	@Override
	public void put(String key, HK hashKey, HV value) {
		hashOps.put(key, hashKey, value);
	}

	@Override
	public List<HV> values(String key) {
		return hashOps.values(key);
	}

	@Override
	public Map<HK, HV> entries(String key) {
		return hashOps.entries(key);
	}

	@Override
	public HV get(String key, HK hashKey) {
		return hashOps.get(key, hashKey);
	}

	@Override
	public Boolean hasKey(String key, HK hashKey) {
		return hashOps.hasKey(key, hashKey);
	}

	@Override
	public Set<HK> keys(String key) {
		return hashOps.keys(key);
	}

	@Override
	public Long size(String key) {
		return hashOps.size(key);
	}

	@Override
	public Double increment(String key, HK hashKey, Double delta) {
		return hashOps.increment(key, hashKey, delta);
	}

	@Override
	public Long increment(String key, HK hashKey, long delta) {
		return hashOps.increment(key, hashKey, delta);
	}

	@Override
	public List<HV> multiGet(String key, Collection<HK> hashKeys) {
		return hashOps.multiGet(key, hashKeys);
	}

	@Override
	public void putAll(String key, Map<? extends HK, ? extends HV> m) {
		hashOps.putAll(key, m);
	}

	@Override
	public Boolean putIfAbsent(String key, HK hashKey, HV value) {
		return hashOps.putIfAbsent(key, hashKey, value);
	}

	@Override
	public Cursor<Entry<HK, HV>> scan(String key, ScanOptions options) {
		return hashOps.scan(key, options);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long delete(String key, HK... hashKeys) {
		return hashOps.delete(key, hashKeys);
	}

}
