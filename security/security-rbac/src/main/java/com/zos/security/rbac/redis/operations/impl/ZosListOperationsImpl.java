package com.zos.security.rbac.redis.operations.impl;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zos.security.rbac.redis.operations.ZosListOperations;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis List 操作
 * 
 * @author 01Studio
 *
 * @param <V> 类型
 */
@Getter
@Setter
@Component
public class ZosListOperationsImpl<V> implements ZosListOperations<V>{
	
	@Autowired
	private RedisTemplate<String, V> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private ListOperations<String, V> listOps;
	
	@Override
	public Long leftPush(String key, V value) {
		return listOps.leftPush(key, value);
	}

	@Override
	public V index(String key, Long index) {
		return listOps.index(key, index);
	}

	@Override
	public List<V> range(String key, Long start, Long end) {
		return listOps.range(key, start, end);
	}

	@Override
	public Long leftPush(String key, V pivot, V value) {
		return listOps.leftPush(key, pivot, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long leftPushAll(String key, V... values) {
		return listOps.leftPushAll(key, values);
	}

	@Override
	public Long leftPushAll(String key, Collection<V> values) {
		return listOps.leftPushAll(key, values);
	}

	@Override
	public Long leftPushIfPresent(String key, V value) {
		return listOps.leftPushIfPresent(key, value);
	}

	@Override
	public Long rightPush(String key, V value) {
		return listOps.rightPush(key, value);
	}

	@Override
	public Long rightPush(String key, V pivot, V value) {
		return listOps.rightPush(key, pivot, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long rightPushAll(String key, V... values) {
		return listOps.rightPushAll(key, values);
	}

	@Override
	public Long rightPushAll(String key, Collection<V> values) {
		return listOps.rightPushAll(key, values);
	}

	@Override
	public Long rightPushIfPresent(String key, V value) {
		return listOps.rightPushIfPresent(key, value);
	}

	@Override
	public Long size(String key) {
		return listOps.size(key);
	}

	@Override
	public V leftPop(String key) {
		return listOps.leftPop(key);
	}

	@Override
	public V leftPop(String key, Long timeout, TimeUnit unit) {
		return listOps.leftPop(key, timeout, unit);
	}

	@Override
	public V rightPop(String key) {
		return listOps.rightPop(key);
	}

	@Override
	public V rightPop(String key, Long timeout, TimeUnit unit) {
		return listOps.rightPop(key, timeout, unit);
	}

	@Override
	public V rightPopAndLeftPush(String sourceKey, String destinationKey) {
		return listOps.rightPopAndLeftPush(sourceKey, destinationKey);
	}

	@Override
	public V rightPopAndLeftPush(String sourceKey, String destinationKey, Long timeout, TimeUnit unit) {
		return listOps.rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
	}

	@Override
	public void set(String key, Long index, V value) {
		listOps.set(key, index, value);
	}

	@Override
	public Long remove(String key, Long count, V value) {
		return listOps.remove(key, count, value);
	}

	@Override
	public void trim(String key, Long start, Long end) {
		listOps.trim(key, start, end);
	}

}
