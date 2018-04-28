package com.zos.security.rbac.redis.operations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 字符串操作接口
 * 
 * @author 01Studio
 *
 */
public interface ZosValueOperations {
	
	/**
	 * 新增一个字符串类型的值, key 是键, value 是值
	 * 
	 * @param key 键
	 * @param value 值
	 */
	public void set(String key, String value);

	/**
	 * 获取 key 键对应的值
	 * 
	 * @param key 键
	 * @return value 值
	 */
	public String get(String key);

	/**
	 * 在原有值的基础上新增字符串到末尾
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 整型数值
	 */
	public Integer append(String key, String value);
	
	/**
	 * 截取 key 键对应值得字符串, 从开始下标位置开始到结束下标位置(包含结束下标)的字符串
	 * 
	 * @param key 键
	 * @param start 开始下标位置
	 * @param end 结束下标位置
	 * @return value 值
	 */
	public String get(String key, Long start, Long end);
	
	/**
	 * 获取原来 key 键对应的值, 并重新赋新值
	 * 
	 * @param key 键
	 * @param value 新值
	 * @return value 新值
	 */
	public String getAndSet(String key, String value);
	
	/**
	 * key 键对应的值 value 对应的 ascii 码, 在 offset 的位置(从左向右数)变为 value
	 * 
	 * @param key 键
	 * @param offset 下标
	 * @param value 最终要变的值, 0 或 1
	 * @return boolean
	 */
	public Boolean setBit(String key, Long offset, Boolean value);
	
	/**
	 * 判断指定的位置 ASCII 码的 bit 位是否为 1
	 * 
	 * @param key 键
	 * @param offset 下标
	 * @return boolean
	 */
	public Boolean getBit(String key, Long offset);
	
	/**
	 * 获取指定字符串的长度
	 * 
	 * @param key 键
	 * @return long 长度
	 */
	public Long size(String key);
	
	/**
	 * 以增量的方式将 double 值存储在变量中
	 * 
	 * @param key 键
	 * @param delta 增量
	 * @return double 结果
	 */
	public Double increment(String key, Double delta);
	
	/**
	 * 以增量的方式将 long 值存储在变量中
	 * 
	 * @param key 键
	 * @param delta 增量
	 * @return long 结果
	 */
	public Long increment(String key, Long delta);
	
	/**
	 * 如果键不存在则新增, 存在则不改变已经有的值
	 * 
	 * @param key 键
	 * @param value 新值
	 * @return boolean
	 */
	public Boolean setIfAbsent(String key, String value);
	
	/**
	 * 设置变量值的过期时间
	 * 
	 * @param key 键
	 * @param value 值
	 * @param timeout 过期时间
	 * @param unit 时间单位
	 */
	public void set(String key, String value, Long timeout, TimeUnit unit);
	
	/**
	 * 覆盖从指定位置开始的值
	 * 
	 * @param key 键
	 * @param value 值
	 * @param offset 下标
	 */
	public void set(String key, String value, long offset);
	
	/**
	 * 设置 map 集合到 redis
	 * 
	 * @param map 数据
	 */
	public void multiSet(Map<String, String> map);
	
	/**
	 * 根据集合取出对应的value值
	 * 
	 * @param keys 键
	 * @return 值集合
	 */
	public List<String> multiGet(Collection<String> keys);
	
	/**
	 * 如果对应的 map 集合名称不存在, 则添加, 如果存在则不做修改
	 * 
	 * @param map 集合
	 * @return boolean
	 */
	public Boolean multiSetIfAbsent(Map<String, String> map);
}
