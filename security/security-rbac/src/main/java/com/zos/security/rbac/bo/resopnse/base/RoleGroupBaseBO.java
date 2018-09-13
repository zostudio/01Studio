package com.zos.security.rbac.bo.resopnse.base;

import com.zos.security.rbac.support.enums.RoleType;
import lombok.Data;

@Data
public class RoleGroupBaseBO {

	/**
	 * 数据库表主键
	 */
	private String id;
	
	/**
	 * 角色组名称
	 */
	private String name;

	/**
	 * 角色类型
	 */
	private RoleType type;
	
	/**
	 * 角色组描述
	 */
	private String description;
}
