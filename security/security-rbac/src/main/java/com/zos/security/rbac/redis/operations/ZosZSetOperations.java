package com.zos.security.rbac.redis.operations;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public interface ZosZSetOperations<V> {

	/**
	 * 添加元素到变量中同时指定元素的分值
	 * 
	 * @param key 键
	 * @param value 值
	 * @param score 分数
	 * @return boolean
	 */
	public Boolean add(String key, V value, Double score);

	/**
	 * 获取变量指定区间的元素
	 * 
	 * @param key 键
	 * @param start 起始位置
	 * @param end 终止位置
	 * @return 值集合
	 */
	public Set<V> range(String key, Long start, Long end);
	 
	/**
	 * 用于获取满足非 score 的排序取值, 这个排序只有在有相同分数的情况下才能使用, 如果有不同的分数则返回值不确定
	 * 
	 * @param key 键
	 * @param range Range
	 * @return 值集合
	 */
	public Set<V> rangeByLex(String key, Range range);
	
	/**
	 * 用于获取满足非 score 的设置下标开始的长度排序取值
	 * 
	 * @param key 键
	 * @param range range
	 * @param limit 限定值
	 * @return 值集合
	 */
	public Set<V> rangeByLex(String key, Range range, Limit limit);
	
	/**
	 * 通过TypedTuple方式新增数据
	 * 
	 * @param key 键
	 * @param tuples TypedTuple
	 * @return long
	 */
	public Long add(String key, Set<TypedTuple<V>> tuples);
	
	/**
	 * 根据设置的 score 获取区间值
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @return 值集合
	 */
	public Set<V> rangeByScore(String key, Double min, Double max);
	
	/**
	 * 根据设置的 score 获取区间值从给定下标和给定长度获取最终值
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @param offset 下标
	 * @param count 数量
	 * @return 值集合
	 */
	public Set<V> rangeByScore(String key, Double min, Double max, Long offset, Long count);

	/**
	 * 获取 RedisZSetCommands.Tuples 的区间值
	 * 
	 * @param key 键
	 * @param start 起始位置
	 * @param end 终止位置
	 * @return 集合
	 */
	public Set<TypedTuple<V>> rangeWithScores(String key, Long start, Long end);

	/**
	 * 获取 RedisZSetCommands.Tuples 的区间值通过分值
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @return 集合
	 */
	public Set<TypedTuple<V>> rangeByScoreWithScores(String key, Double min, Double max);
	
	/**
	 * 获取 RedisZSetCommands.Tuples 的区间值从给定下标和给定长度获取最终值通过分值
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @param offset 下标
	 * @param count 数量
	 * @return 值集合
	 */
	public Set<TypedTuple<V>> rangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count);
	
	/**
	 * 获取区间值的个数
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @return long
	 */
	public Long count(String key, Double min, Double max);
	
	/**
	 * 获取变量中元素的索引, 下标开始位置为 0
	 * 
	 * @param key 键
	 * @param value 值
	 * @return long
	 */
	public Long rank(String key, V value);

	/**
	 * 匹配获取键值对, ScanOptions.NONE 为获取全部键值对; ScanOptions.scanOptions().match("C").build() 匹配获取键位 map1 的键值对, 不能模糊匹配
	 * 
	 * @param key 键
	 * @param options 匹配项
	 * @return 值集合
	 */
	public Cursor<TypedTuple<V>> scan(String key, ScanOptions options);
	
	/**
	 * 获取元素的分值
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 分数值
	 */
	public Double score(String key, V value);
	
	/**
	 * 获取变量中元素的个数
	 * 
	 * @param key 键
	 * @return long
	 */
	public Long zCard(String key);

	/**
	 * 修改变量中的元素的分值
	 * 
	 * @param key 键
	 * @param value 值
	 * @param delta 分数值
	 * @return double
	 */
	public Double incrementScore(String key, V value, Double delta);
	
	/**
	 * 索引倒序排列指定区间元素
	 * 
	 * @param key 键
	 * @param start 起始位置
	 * @param end 终止位置
	 * @return 值集合
	 */
	public Set<V> reverseRange(String key, Long start, Long end);
	
	/**
	 * 倒序排列指定分值区间元素
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @return 值集合
	 */
	public Set<V> reverseRangeByScore(String key, Double min, Double max);
	
	/**
	 * 倒序排列从给定下标和给定长度分值区间元素
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @param offset 下标
	 * @param count 数量
	 * @return 值集合
	 */
	public Set<V> reverseRangeByScore(String key, Double min, Double max, Long offset, Long count);
	
	/**
	 * 倒序排序获取 RedisZSetCommands.Tuples 的分值区间值
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上线
	 * @return 值集合
	 */
	public Set<TypedTuple<V>> reverseRangeByScoreWithScores(String key, Double min, Double max);
	
	/**
	 * 倒序排序获取 RedisZSetCommands.Tuples 的从给定下标和给定长度分值区间值
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @param offset 下标
	 * @param count 数量
	 * @return 值集合
	 */
	public Set<TypedTuple<V>> reverseRangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count);
	
	/**
	 * 索引倒序排列区间值
	 * 
	 * @param key 键
	 * @param start 起始位置
	 * @param end 结束位置
	 * @return 值集合
	 */
	Set<TypedTuple<V>> reverseRangeWithScores(String key, Long start, Long end);
	
	/**
	 * 获取倒序排列的索引值
	 * 
	 * @param key 键
	 * @param value 值
	 * @return long
	 */
	public Long reverseRank(String key, V value);
	
	/**
	 * 获取两个变量的交集存放到第三个变量里面
	 * 
	 * @param key 旧键
	 * @param otherKey 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long intersectAndStore(String key, String otherKey, String destKey);
	
	/**
	 * 获取多个变量的交集存放到第三个变量里面
	 * 
	 * @param key 旧键
	 * @param otherKeys 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey);
	
	/**
	 * 获取两个变量的合集存放到第三个变量里面
	 * 
	 * @param key 旧键
	 * @param otherKey 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long unionAndStore(String key, String otherKey, String destKey);
	
	/**
	 * 获取多个变量的合集存放到第三个变量里面
	 * 
	 * @param key 旧键
	 * @param otherKeys 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long unionAndStore(String key, Collection<String> otherKeys, String destKey);

	/**
	 * 批量移除元素根据元素值
	 * 
	 * @param key 键
	 * @param values 值列表
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public Long remove(String key, V... values);
	
	/**
	 * 根据分值移除区间元素
	 * 
	 * @param key 键
	 * @param min 下限
	 * @param max 上限
	 * @return long
	 */
	public Long removeRangeByScore(String key, Double min, Double max);
	
	/**
	 * 根据索引值移除区间元素
	 * 
	 * @param key 键
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return long
	 */
	public Long removeRange(String key, Long start, Long end);

}
