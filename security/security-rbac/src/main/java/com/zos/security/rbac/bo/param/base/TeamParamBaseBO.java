package com.zos.security.rbac.bo.param.base;

import lombok.Data;

@Data
public class TeamParamBaseBO {
	
	/**
	 * 团队名称
	 */
	private String name;
	
	/**
	 * 团队描述
	 */
	private String description;
}
