package com.zos.security.rbac.bo;

import java.util.Date;

import lombok.Data;

@Data
public class DepartmentBO {
	
	/**
	 * 数据库表主键
	 */
	private Long id;
	
	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;
	
	/**
	 * 序号
	 */
	private Integer sort;
	
	/**
	 * 描述
	 */
	private String description;

}
