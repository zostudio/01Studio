package com.zos.security.rbac.bo;

import com.zos.security.rbac.support.RoleType;
import lombok.Data;

@Data
public class RoleBO {
	
	/**
	 * 数据库表主键
	 */
	private Long id;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色类型
	 */
	private RoleType type;

	/**
	 * 角色描述
	 */
	private String description;
	
}
