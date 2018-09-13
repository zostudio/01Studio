package com.zos.security.rbac.bo;

import java.util.Date;

import lombok.Data;

@Data
public class RoleGroupBO {
	
	/**
	 * 数据库表主键
	 */
	private String id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;

	/**
	 * 角色名称
	 */
	private String name;
	
}
