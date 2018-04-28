package com.zos.security.rbac.redis.operations.impl;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.zos.security.rbac.redis.operations.ZosZSetOperations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ZosZSetOperationsImpl<V> implements ZosZSetOperations<V> {
	
	@Autowired
	private RedisTemplate<String, V> redisTemplate;
	
	@Resource(name = "redisTemplate")
	private ZSetOperations<String, V> zsetOps;

	@Override
	public Boolean add(String key, V value, Double score) {
		return zsetOps.add(key, value, score);
	}

	@Override
	public Set<V> range(String key, Long start, Long end) {
		return zsetOps.range(key, start, end);
	}

	@Override
	public Set<V> rangeByLex(String key, Range range) {
		return zsetOps.rangeByLex(key, range);
	}

	@Override
	public Set<V> rangeByLex(String key, Range range, Limit limit) {
		return zsetOps.rangeByLex(key, range, limit);
	}

	@Override
	public Long add(String key, Set<TypedTuple<V>> tuples) {
		return zsetOps.add(key, tuples);
	}

	@Override
	public Set<V> rangeByScore(String key, Double min, Double max) {
		return zsetOps.rangeByScore(key, min, max);
	}

	@Override
	public Set<V> rangeByScore(String key, Double min, Double max, Long offset, Long count) {
		return zsetOps.rangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<TypedTuple<V>> rangeWithScores(String key, Long start, Long end) {
		return zsetOps.rangeWithScores(key, start, end);
	}

	@Override
	public Set<TypedTuple<V>> rangeByScoreWithScores(String key, Double min, Double max) {
		return zsetOps.rangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<TypedTuple<V>> rangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count) {
		return zsetOps.rangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Long count(String key, Double min, Double max) {
		return zsetOps.count(key, min, max);
	}

	@Override
	public Long rank(String key, V value) {
		return zsetOps.rank(key, value);
	}

	@Override
	public Cursor<TypedTuple<V>> scan(String key, ScanOptions options) {
		return zsetOps.scan(key, options);
	}

	@Override
	public Double score(String key, V value) {
		return zsetOps.score(key, value);
	}

	@Override
	public Long zCard(String key) {
		return zsetOps.zCard(key);
	}

	@Override
	public Double incrementScore(String key, V value, Double delta) {
		return zsetOps.incrementScore(key, value, delta);
	}

	@Override
	public Set<V> reverseRange(String key, Long start, Long end) {
		return zsetOps.reverseRange(key, start, end);
	}

	@Override
	public Set<V> reverseRangeByScore(String key, Double min, Double max) {
		return zsetOps.reverseRangeByScore(key, min, max);
	}

	@Override
	public Set<V> reverseRangeByScore(String key, Double min, Double max, Long offset, Long count) {
		return zsetOps.reverseRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<TypedTuple<V>> reverseRangeByScoreWithScores(String key, Double min, Double max) {
		return zsetOps.reverseRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<TypedTuple<V>> reverseRangeByScoreWithScores(String key, Double min, Double max, Long offset,
			Long count) {
		return zsetOps.reverseRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<TypedTuple<V>> reverseRangeWithScores(String key, Long start, Long end) {
		return zsetOps.reverseRangeWithScores(key, start, end);
	}

	@Override
	public Long reverseRank(String key, V value) {
		return zsetOps.reverseRank(key, value);
	}

	@Override
	public Long intersectAndStore(String key, String otherKey, String destKey) {
		return zsetOps.intersectAndStore(key, otherKey, destKey);
	}

	@Override
	public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return zsetOps.intersectAndStore(key, otherKeys, destKey);
	}

	@Override
	public Long unionAndStore(String key, String otherKey, String destKey) {
		return zsetOps.unionAndStore(key, otherKey, destKey);
	}

	@Override
	public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return zsetOps.unionAndStore(key, otherKeys, destKey);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long remove(String key, V... values) {
		return zsetOps.remove(key, values);
	}

	@Override
	public Long removeRangeByScore(String key, Double min, Double max) {
		return zsetOps.removeRangeByScore(key, min, max);
	}

	@Override
	public Long removeRange(String key, Long start, Long end) {
		return zsetOps.removeRange(key, start, end);
	}

}
