package com.zos.security.rbac.bo.resopnse.base;

import lombok.Data;

@Data
public class TeamBaseBO {

	/**
	 * 数据库表主键
	 */
	private String id;
	
	/**
	 * 团队名称
	 */
	private String name;
	
	/**
	 * 团队描述
	 */
	private String description;
}
