package com.zos.security.rbac.dto;

import java.util.Date;

import com.zos.security.rbac.domain.Resource;
import com.zos.security.rbac.domain.Role;

import lombok.Data;

@Data
public class RoleResourceDTO {
	
	/**
	 * 数据库表主键
	 */
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;
	
	/**
	 * 角色
	 */
	private Role role;
	
	/**
	 * 资源
	 */
	private Resource resource;
}
