package com.zos.security.rbac.service;

import java.util.List;

import com.zos.security.rbac.dto.RoleInfo;

/**
 * 角色服务
 * 
 * @author 01Studio
 *
 */
public interface RoleService {
	
	/**
	 * 创建角色
	 * @param roleInfo 角色信息
	 * @return 角色信息
	 */
	RoleInfo create(RoleInfo roleInfo);

	/**
	 * 修改角色
	 *
	 * @param roleInfo 角色信息
	 * @return 角色信息
	 */
	RoleInfo update(RoleInfo roleInfo);

	/**
	 * 删除角色
	 *
	 * @param id 角色主键
	 */
	void delete(Long id);

	/**
	 * 获取角色详细信息
	 *
	 * @param id 角色主键
	 * @return 角色信息
	 */
	RoleInfo getInfo(Long id);

	/**
	 * 查询所有角色
	 *
	 * @return 角色信息列表
	 */
	List<RoleInfo> findAll();

	/**
	 * @param id 角色主键
	 * @return 资源列表
	 */
	String[] getRoleResources(Long id);

	/**
	 * @param id 角色主键
	 * @param ids 资源列表
	 */
	void setRoleResources(Long id, String ids);

}
