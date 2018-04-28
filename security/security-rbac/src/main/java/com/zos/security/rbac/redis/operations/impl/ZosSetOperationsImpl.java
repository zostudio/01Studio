package com.zos.security.rbac.redis.operations.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import com.zos.security.rbac.redis.operations.ZosSetOperations;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis Set 操作
 * 
 * @author 01Studio
 *
 * @param <V> 值
 */
@Getter
@Setter
@Component
public class ZosSetOperationsImpl<V> implements ZosSetOperations<V> {
	
	@Autowired
	private RedisTemplate<String, V> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private SetOperations<String, V> setOps;

	@Override
	@SuppressWarnings("unchecked")
	public Long add(String key, V... values) {
		return setOps.add(key, values);
	}

	@Override
	public Set<V> members(String key) {
		return setOps.members(key);
	}

	@Override
	public Long size(String key) {
		return setOps.size(key);
	}

	@Override
	public V randomMember(String key) {
		return setOps.randomMember(key);
	}

	@Override
	public List<V> randomMembers(String key, Long count) {
		return setOps.randomMembers(key, count);
	}

	@Override
	public Boolean isMember(String key, V value) {
		return setOps.isMember(key, value);
	}

	@Override
	public Boolean move(String key, V value, String destKey) {
		return setOps.move(key, value, destKey);
	}

	@Override
	public V pop(String key) {
		return setOps.pop(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long remove(String key, V... values) {
		return setOps.remove(key, values);
	}

	@Override
	public Cursor<V> scan(String key, ScanOptions options) {
		return setOps.scan(key, options);
	}

	@Override
	public Set<V> difference(String key, Collection<String> otherKeys) {
		return setOps.difference(key, otherKeys);
	}

	@Override
	public Set<V> difference(String key, String otherKey) {
		return setOps.difference(key, otherKey);
	}

	@Override
	public Long differenceAndStore(String key, String otherKey, String destKey) {
		return setOps.differenceAndStore(key, otherKey, destKey);
	}

	@Override
	public Long differenceAndStore(String key, Collection<String> otherKeys, String destKey) {
		return setOps.differenceAndStore(key, otherKeys, destKey);
	}

	@Override
	public Set<V> distinctRandomMembers(String key, Long count) {
		return setOps.distinctRandomMembers(key, count);
	}

	@Override
	public Set<V> intersect(String key, String otherKey) {
		return setOps.intersect(otherKey, otherKey);
	}

	@Override
	public Set<V> intersect(String key, Collection<String> otherKeys) {
		return setOps.intersect(key, otherKeys);
	}

	@Override
	public Long intersectAndStore(String key, String otherKey, String destKey) {
		return setOps.intersectAndStore(otherKey, otherKey, destKey);
	}

	@Override
	public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return setOps.intersectAndStore(key, otherKeys, destKey);
	}

	@Override
	public Set<V> union(String key, String otherKey) {
		return setOps.union(otherKey, otherKey);
	}

	@Override
	public Set<V> union(String key, Collection<String> otherKeys) {
		return setOps.union(key, otherKeys);
	}

	@Override
	public Long unionAndStore(String key, String otherKey, String destKey) {
		return setOps.unionAndStore(otherKey, otherKey, destKey);
	}

	@Override
	public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return setOps.unionAndStore(key, otherKeys, destKey);
	}

}
