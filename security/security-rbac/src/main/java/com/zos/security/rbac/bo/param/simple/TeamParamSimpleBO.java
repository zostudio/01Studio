package com.zos.security.rbac.bo.param.simple;

import lombok.Data;

@Data
public class TeamParamSimpleBO {
	
	/**
	 * 团队名称
	 */
	private String name;
	
	/**
	 * 团队描述
	 */
	private String description;
}
