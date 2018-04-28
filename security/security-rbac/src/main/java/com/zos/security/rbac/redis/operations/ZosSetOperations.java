package com.zos.security.rbac.redis.operations;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

/**
 * Set 操作接口
 * 
 * @author 01Studio
 *
 * @param <V> 值
 */
public interface ZosSetOperations<V> {
	
	/**
	 * 向变量中批量添加值
	 * 
	 * @param key 键
	 * @param values 不定参数个数值
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public Long add(String key, V... values);
	
	/**
	 * 获取变量中的值
	 * 
	 * @param key 键
	 * @return 值集合
	 */
	public Set<V> members(String key);
	 
	/**
	 * 获取变量中值的长度
	 * 
	 * @param key 键
	 * @return long
	 */
	public Long size(String key);
	 
	/**
	 * 随机获取变量中的元素
	 * 
	 * @param key 键
	 * @return 值
	 */
	public V randomMember(String key);
	 
	/**
	 * 随机获取变量中指定个数的元素
	 * 
	 * @param key 键
	 * @param count 数量
	 * @return 值集合
	 */
	public List<V> randomMembers(String key, Long count);
	 
	/**
	 * 检查给定的元素是否在变量中
	 * 
	 * @param key 键
	 * @param value 值
	 * @return boolean
	 */
	public Boolean isMember(String key, V value);
	
	/**
	 * 转移变量的元素值到目的变量
	 * 
	 * @param key 旧键
	 * @param value 值
	 * @param destKey 新键
	 * @return boolean
	 */
	public Boolean move(String key, V value, String destKey);
	 
	/**
	 * 弹出变量中的元素
	 * 
	 * @param key 键
	 * @return 值
	 */
	public V pop(String key);
	
	/**
	 * 批量移除变量中的元素
	 * 
	 * @param key 键
	 * @param values 不定参数个数值
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public Long remove(String key, V... values);
	
	/**
	 * 匹配获取键值对, ScanOptions.NONE 为获取全部键值对; ScanOptions.scanOptions().match("C").build() 匹配获取键位 map1 的键值对, 不能模糊匹配
	 * 
	 * @param key 键
	 * @param options 扫描值
	 * @return 值
	 */
	public Cursor<V> scan(String key, ScanOptions options);
	
	/**
	 * 通过集合求差值
	 * 
	 * @param key 键
	 * @param otherKeys 其他键集合
	 * @return 差值集合
	 */
	public Set<V> difference(String key, Collection<String> otherKeys);
	
	/**
	 * 通过给定的 key 求两个 set 变量的差值
	 * 
	 * @param key 键
	 * @param otherKey 其他键
	 * @return 差值集合
	 */
	public Set<V> difference(String key, String otherKey);

	/**
	 * 将求出来的差值元素保存
	 * 
	 * @param key 旧键
	 * @param otherKey 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long differenceAndStore(String key, String otherKey, String destKey);
	
	/**
	 * 将求出来的差值元素保存
	 * 
	 * @param key 旧键
	 * @param otherKeys 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long differenceAndStore(String key, Collection<String> otherKeys, String destKey);

	/**
	 * 获取去重的随机元素
	 * 
	 * @param key 键
	 * @param count 数量
	 * @return 值集合
	 */
	public Set<V> distinctRandomMembers(String key, Long count);

	/**
	 * 获取两个变量中的交集
	 * 
	 * @param key 键
	 * @param otherKey 其他键
	 * @return 值集合
	 */
	public Set<V> intersect(String key, String otherKey);

	/**
	 * 获取多个变量之间的交集
	 * 
	 * @param key 键
	 * @param otherKeys 其他键集合
	 * @return 值集合
	 */
	public Set<V> intersect(String key, Collection<String> otherKeys);

	/**
	 * 获取两个变量交集后保存到最后一个参数上
	 * 
	 * @param key 旧键
	 * @param otherKey 其他键
	 * @param destKey 新键 
	 * @return long
	 */
	public Long intersectAndStore(String key, String otherKey, String destKey);
	
	/**
	 * 获取多个变量的交集并保存到最后一个参数上
	 * 
	 * @param key 旧键
	 * @param otherKeys 其他键集合
	 * @param destKey 新键
	 * @return long
	 */
	public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey);

	/**
	 * 获取两个变量的合集
	 * 
	 * @param key 键
	 * @param otherKey 其他键
	 * @return 值集合
	 */
	public Set<V> union(String key, String otherKey);

	/**
	 * 获取多个变量的合集
	 * 
	 * @param key 键
	 * @param otherKeys 其他键集合
	 * @return 值集合
	 */
	public Set<V> union(String key, Collection<String> otherKeys);

	/**
	 * 获取两个变量合集后保存到最后一个参数上
	 * 
	 * @param key 旧键
	 * @param otherKey 其他键
	 * @param destKey 新键
	 * @return long
	 */
	public Long unionAndStore(String key, String otherKey, String destKey);

	/**
	 * 获取多个变量的合集并保存到最后一个参数上
	 * 
	 * @param key 旧键
	 * @param otherKeys 其他键集合
	 * @param destKey 新键
	 * @return long
	 */
	public Long unionAndStore(String key, Collection<String> otherKeys, String destKey);
	
}
