package com.zos.security.rbac.bo;

import java.util.Date;

import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.domain.User;

import lombok.Data;

@Data
public class UserRoleBO {
	
	/**
	 * 数据库表主键
	 */
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;
	
	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 角色
	 */
	private Role role;
}
