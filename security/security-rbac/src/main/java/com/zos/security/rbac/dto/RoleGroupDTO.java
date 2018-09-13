package com.zos.security.rbac.dto;

import lombok.*;

import java.util.Date;

@Data
public class RoleGroupDTO {
	
	public interface SimpleView {};
	public interface DetailView extends SimpleView {};

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
