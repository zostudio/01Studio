package com.zos.security.rbac.redis.dao;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.zos.security.rbac.redis.operations.ZosHashOperations;
import com.zos.security.rbac.redis.operations.ZosListOperations;
import com.zos.security.rbac.redis.operations.ZosSetOperations;
import com.zos.security.rbac.redis.operations.ZosValueOperations;
import com.zos.security.rbac.redis.operations.ZosZSetOperations;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis 基础操作
 * 
 * @author 01Studio
 *
 * @param <V> 数据
 * @param <HK> 哈希键
 * @param <HV> 哈希值
 */
@Getter
@Setter
public abstract class BaseRedisDAO<V, HK, HV> implements RedisCommonDAO<V, HK, HV>{
	
	@Autowired
	private RedisTemplate<String, ?> redisTemplate;
	
	@Autowired
	private ZosValueOperations opsForValue;
	
	@Autowired
	private ZosListOperations<V> opsForList;
	
	@Autowired
	private ZosSetOperations<V> opsForSet;
	
	@Autowired
	private ZosZSetOperations<V> opsForZSet;
	
	@Autowired
	private ZosHashOperations<HK, HV> opsForHash;
	

	@Override
	public RedisTemplate<String, ?> redisTemplate() {
		return this.redisTemplate;
	}
	
	@Override
	public ZosValueOperations opsForValue() {
		return this.opsForValue;
	}
	
	@Override
	public ZosListOperations<V> opsForList() {
		return this.opsForList;
	}
	
	@Override
	public ZosSetOperations<V> opsForSet() {
		return this.opsForSet;
	}
	
	@Override
	public ZosZSetOperations<V> opsForZSet() {
		return this.opsForZSet;
	}
	
	@Override
	public ZosHashOperations<HK, HV> opsForHash() {
		return this.opsForHash;
	}
	
	@Override
	public RedisTemplate<String, ?> redisTemplate(RedisTemplate<String, ?> redisTemplate) {
		return this.redisTemplate = redisTemplate;
	}
	
	@Override
	public ZosValueOperations opsForValue(ZosValueOperations opsForValue) {
		return this.opsForValue = opsForValue;
	}
	
	@Override
	public ZosListOperations<V> opsForList(ZosListOperations<V> opsForList) {
		return this.opsForList = opsForList;
	}
	
	@Override
	public ZosSetOperations<V> opsForSet(ZosSetOperations<V> opsForSet) {
		return this.opsForSet = opsForSet;
	}
	
	@Override
	public ZosZSetOperations<V> opsForZSet(ZosZSetOperations<V> opsForZSet) {
		return this.opsForZSet = opsForZSet;
	}
	
	@Override
	public ZosHashOperations<HK, HV> opsForHash(ZosHashOperations<HK, HV> opsForHash) {
		return this.opsForHash = opsForHash;
	}
	
	/**
	 * 设置数据生存时长
	 * 
	 * @param key 键
	 * @param timeout 时长
	 * @param unit 时间单位
	 * @return boolean
	 */
	public Boolean expire(String key, final Long timeout, final TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}
	
	/**
	 * 设置数据过期时间
	 * 
	 * @param key 键
	 * @param date 过期时间
	 * @return boolean
	 */
	public Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}
	
	/**
	 * 移除指定的键值
	 * 
	 * @param key 键
	 * @return boolean
	 */
	public Boolean persist(String key) {
		return redisTemplate.persist(key);
	}
	
	/**
	 * 清空选中的数据库
	 * 
	 * @return boolean
	 */
	@Override
	public Boolean flushDB() {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
            	connection.flushDb();
                return true;
            }
        });
	}
	
	/**
	 * 数据库大小
	 * 
	 * @return long
	 */
	@Override
	public Long dbSize() {
		return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
            	return connection.dbSize();
            }
        });
	}
	
	/**
	 * 检测是否联通
	 * 
	 * @return string
	 */
	@Override
	public String ping() {
		return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
            	return connection.ping();
            }
        });
	}
	
}
