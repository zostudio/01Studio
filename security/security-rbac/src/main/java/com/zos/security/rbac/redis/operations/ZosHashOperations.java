package com.zos.security.rbac.redis.operations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

/**
 * Hash 操作接口
 * 
 * @author 01Studio
 *
 */
public interface ZosHashOperations<HK, HV> {
	
	/**
	 * 新增 HashMap 值
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 哈希值
	 */
	public void put(String key, HK hashKey, HV value);
	
	/**
	 * 获取指定变量中的 HashMap 值
	 * @param key 键
	 * @return 哈希值集合
	 */
	public List<HV> values(String key); 
	
	/**
	 * 获取变量中的键值对
	 * 
	 * @param key 键
	 * @return 哈希键值
	 */
	public Map<HK, HV> entries(String key);
	
	/**
	 * 获取变量中的指定 Map 键是否有值, 如果存在该 Map 键则获取值, 没有则返回 null
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return 哈希值
	 */
	public HV get(String key, HK hashKey);
	
	/**
	 * 判断变量中是否有指定的 Map 键
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return boolean
	 */
	public Boolean hasKey(String key, HK hashKey);
	 
	/**
	 * 获取变量中的键
	 * 
	 * @param key 键
	 * @return 哈希键集合
	 */
	public Set<HK> keys(String key);
	 
	/**
	 * 获取变量的长度
	 * 
	 * @param key 键
	 * @return long
	 */
	public Long size(String key);
	 
	/**
	 * 使变量中的键以 double 值的大小进行自增长
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param delta 增量
	 * @return double
	 */
	public Double increment(String key, HK hashKey, Double delta);
	 
	/**
	 * 使变量中的键以 long 值的大小进行自增长
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param delta 增量
	 * @return long
	 */
	public Long increment(String key, HK hashKey, long delta);
	 
	/**
	 * 以集合的方式获取变量中的值
	 * 
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @return 哈希值集合
	 */
	public List<HV> multiGet(String key, Collection<HK> hashKeys);
	 
	/**
	 * 以 map 集合的形式添加键值对
	 * 
	 * @param key 键
	 * @param m 哈希键值
	 */
	public void putAll(String key, Map<? extends HK, ? extends HV> m);
	 
	/**
	 * 如果变量值存在, 在变量中可以添加不存在的的键值对, 如果变量不存在, 则新增一个变量, 同时将键值对添加到该变量
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 哈希值
	 * @return boolean
	 */
	public Boolean putIfAbsent(String key, HK hashKey, HV value);
	
	/**
	 * 匹配获取键值对, ScanOptions.NONE 为获取全部键对, ScanOptions.scanOptions().match("map1").build() 匹配获取键位 map1 的键值对, 不能模糊匹配
	 * 
	 * @param key 键
	 * @param options 扫描
	 * @return Cursor
	 */
	public Cursor<Map.Entry<HK, HV>> scan(String key, ScanOptions options);
	
	/**
	 * 删除变量中的键值对, 可以传入多个参数, 删除多个键值对
	 * 
	 * @param key 键
	 * @param hashKeys 不定参数个数哈希键
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public Long delete(String key, HK... hashKeys);
}
