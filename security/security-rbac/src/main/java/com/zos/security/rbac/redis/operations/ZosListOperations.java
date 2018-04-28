package com.zos.security.rbac.redis.operations;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * List 操作接口
 * 
 * @author 01Studio
 *
 */
public interface ZosListOperations<V> {
	
	/**
	 * 在变量左边添加元素值
	 * 
	 * @param key 键
	 * @param value 值
	 * @return long
	 */
	public Long leftPush(String key, V value);
	
	/**
	 * 获取集合指定位置的值
	 * 
	 * @param key 键
	 * @param index 索引
	 * @return V
	 */
	public V index(String key, Long index);
	
	/**
	 * 获取指定区间的值
	 * 
	 * @param key 键
	 * @param start 起始位置
	 * @param end 结束位置
	 * @return 值集合
	 */
	public List<V> range(String key, Long start, Long end);
	
	/**
	 * 把最后一个参数值放到指定集合的第一个出现中间参数的前面, 如果中间参数值存在的话
	 * 
	 * @param key 键
	 * @param pivot 参考位置的值
	 * @param value 值
	 * @return long
	 */
	public Long leftPush(String key, V pivot, V value);

	/**
	 * 以不定参数个数的方式向左边批量添加参数元素
	 * 
	 * @param key 键
	 * @param values 值集合
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public Long leftPushAll(String key, V... values);

	/**
	 * 以集合的方式向左边批量添加元素
	 * 
	 * @param key 键
	 * @param values 值集合
	 * @return long
	 */
	public Long leftPushAll(String key, Collection<V> values);
	
	/**
	 * 如果存在集合则添加元素
	 * 
	 * @param key 键
	 * @param value 值
	 * @return long
	 */
	public Long leftPushIfPresent(String key, V value);
	
	/**
	 * 向集合最右边添加元素
	 * 
	 * @param key 键
	 * @param value 值
	 * @return long
	 */
	public Long rightPush(String key, V value);
	
	/**
	 * 向集合中第一次出现第二个参数变量元素的右边添加第三个参数变量的元素值
	 * 
	 * @param key 键
	 * @param pivot 参考位置的值
	 * @param value 值
	 * @return long
	 */
	public Long rightPush(String key, V pivot, V value);
	
	/**
	 * 以不定参数个数的方式向右边批量添加元素
	 * 
	 * @param key 键
	 * @param values 值
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public Long rightPushAll(String key, V... values);
	
	/**
	 * 以集合方式向右边批量添加元素
	 * 
	 * @param key 键
	 * @param values 值
	 * @return long
	 */
	public Long rightPushAll(String key, Collection<V> values);
	
	/**
	 * 向已存在的集合中添加元素
	 * 
	 * @param key 键
	 * @param value 值
	 * @return long
	 */
	public Long rightPushIfPresent(String key, V value);
	
	/**
	 * 获取集合长度
	 * 
	 * @param key 键
	 * @return long
	 */
	public Long size(String key);
	
	/**
	 * 移除集合中的左边第一个元素
	 * 
	 * @param key 键
	 * @return 值
	 */
	public V leftPop(String key);

	/**
	 * 移除集合中左边的元素在等待的时间里, 如果超过等待的时间仍没有元素则退出
	 * 
	 * @param key 将
	 * @param timeout 时长
	 * @param unit 时间单位
	 * @return 值
	 */
	public V leftPop(String key, Long timeout, TimeUnit unit);
	
	/**
	 * 移除集合中右边的元素
	 * 
	 * @param key 键
	 * @return 值
	 */
	public V rightPop(String key);
	
	/**
	 * 移除集合中右边的元素在等待的时间里, 如果超过等待的时间仍没有元素则退出
	 * 
	 * @param key 键
	 * @param timeout 时长
	 * @param unit 时间单位
	 * @return 值
	 */
	public V rightPop(String key, Long timeout, TimeUnit unit);
	
	/**
	 * 移除集合中右边的元素, 同时在左边加入一个元素
	 * 
	 * @param sourceKey 键(移除)
	 * @param destinationKey 键(添加)
	 * @return 值
	 */
	public V rightPopAndLeftPush(String sourceKey, String destinationKey);

	/**
	 * 移除集合中右边的元素在等待的时间里, 同时在左边添加元素, 如果超过等待的时间仍没有元素则退出
	 * 
	 * @param sourceKey 键(移除)
	 * @param destinationKey 键(添加)
	 * @param timeout 时长
	 * @param unit 时间单位
	 * @return 值
	 */
	public V rightPopAndLeftPush(String sourceKey, String destinationKey, Long timeout, TimeUnit unit);

	/**
	 * 在集合的指定位置插入元素, 如果指定位置已有元素, 则覆盖, 没有则新增, 超过集合下标+n则会报错
	 * @param key 键
	 * @param index 索引
	 * @param value 值
	 */
	public void set(String key, Long index, V value);

	/**
	 * 从存储在键中的列表中删除等于值的元素的第一个计数事件,
	 * count &gt; 0:删除等于从左到右移动的值的第一个元素; count &lt; 0: 删除等于从右到左移动的值的第一个元素; count = 0: 删除等于value的所有元素
	 * @param key 键
	 * @param count 计数
	 * @param value 值
	 * @return long
	 */
	public Long remove(String key, Long count, V value);

	/**
	 * 截取集合元素长度, 保留长度内的数据
	 * 
	 * @param key 键
	 * @param start 起始位置
	 * @param end 终止位置
	 */
	public void trim(String key, Long start, Long end);
}
