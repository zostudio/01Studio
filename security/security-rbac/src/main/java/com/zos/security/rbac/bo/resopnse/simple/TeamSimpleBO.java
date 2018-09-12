package com.zos.security.rbac.bo.resopnse.simple;

import lombok.Data;

@Data
public class TeamSimpleBO {

	/**
	 * 数据库表主键
	 */
	private Long id;
	
	/**
	 * 团队名称
	 */
	private String name;
	
	/**
	 * 团队描述
	 */
	private String description;
}
