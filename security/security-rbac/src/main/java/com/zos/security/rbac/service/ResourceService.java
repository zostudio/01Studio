package com.zos.security.rbac.service;

import com.zos.security.rbac.dto.ResourceInfo;

/**
 * 资源服务
 * 
 * @author 01Studio
 *
 */
public interface ResourceService {
	
	/**
	 * 获取资源树
	 *
	 * @param userId 用户ID
	 * @return 资源信息
	*/
	ResourceInfo getTree(Long userId);

	/**
	 * 根据资源ID获取资源信息
	 *
	 * @param id 资源ID
	 * @return ResourceInfo 资源信息
	*/
	ResourceInfo getInfo(Long id);

	/**
	 * 新增资源
	 *
	 * @param info 页面传入的资源信息
	 * @return ResourceInfo 资源信息
	*/
	ResourceInfo create(ResourceInfo info);

	/**
	 * 更新资源
	 *
	 * @param info 页面传入的资源信息
	 * @return ResourceInfo 资源信息
	*/
	ResourceInfo update(ResourceInfo info);

	/**
	 * 根据指定ID删除资源信息
	 *
	 * @param id 资源ID
	*/
	void delete(Long id);

	/**
	 * 上移/下移资源
	 * @param id 资源主键
	 * @param up 上下移动标识
	 * @return 结果
	 */
	Long move(Long id, boolean up);

}
