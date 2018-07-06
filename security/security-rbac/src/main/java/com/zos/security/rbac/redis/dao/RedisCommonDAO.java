package com.zos.security.rbac.redis.dao;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.zos.security.rbac.redis.operations.ZosHashOperations;
import com.zos.security.rbac.redis.operations.ZosListOperations;
import com.zos.security.rbac.redis.operations.ZosSetOperations;
import com.zos.security.rbac.redis.operations.ZosValueOperations;
import com.zos.security.rbac.redis.operations.ZosZSetOperations;

public interface RedisCommonDAO<V, HK, HV> {
	
	public RedisTemplate<String, ?> redisTemplate();
	
	public ZosValueOperations opsForValue();
	
	public ZosListOperations<V> opsForList();
	
	public ZosSetOperations<V> opsForSet();
	
	public ZosZSetOperations<V> opsForZSet();
	
	public ZosHashOperations<HK, HV> opsForHash();
	
	public RedisTemplate<String, ?> redisTemplate(RedisTemplate<String, ?> redisTemplate);
	
	public ZosValueOperations opsForValue(ZosValueOperations opsForValue);
	
	public ZosListOperations<V> opsForList(ZosListOperations<V> opsForList);
	
	public ZosSetOperations<V> opsForSet(ZosSetOperations<V> opsForSet);
	
	public ZosZSetOperations<V> opsForZSet(ZosZSetOperations<V> opsForZSet);
	
	public ZosHashOperations<HK, HV> opsForHash(ZosHashOperations<HK, HV> opsForHash);
	
	/**
	 * 设置数据生存时长
	 * 
	 * @param key 键
	 * @param timeout 时长
	 * @param unit 时间单位
	 * @return boolean
	 */
	public Boolean expire(String key, final Long timeout, final TimeUnit unit);
	
	/**
	 * 设置数据过期时间
	 * 
	 * @param key 键
	 * @param date 过期时间
	 * @return boolean
	 */
	public Boolean expireAt(String key, Date date);
	
	/**
	 * 移除指定的键值
	 * 
	 * @param key 键
	 * @return boolean
	 */
	public Boolean persist(String key);

	/**
	 * 清空选中的数据库
	 * 
	 * @return boolean
	 */
	public Boolean flushDB();

	/**
	 * 数据库大小
	 * 
	 * @return long
	 */
	public Long dbSize();
	
	/**
	 * 检测是否联通
	 * 
	 * @return string
	 */
	public String ping();
	
	public String valueKey(String correct, V value);
	
	public String listKey(String correct, V value);
	
	public String setKey(String correct, V value);
	
	public String zsetKey(String correct, V value);
	
	public String hashKey(String correct, V value);
	
}
