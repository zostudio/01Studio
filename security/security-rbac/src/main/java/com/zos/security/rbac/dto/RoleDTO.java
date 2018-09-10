package com.zos.security.rbac.dto;

import com.zos.security.rbac.support.RoleType;
import com.zos.security.rbac.validator.constraints.NotAllowValue;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class RoleDTO {

	/**
	 * 数据库表主键
	 */
	@NotAllowValue(message = "主键必须为空")
	private Long id;

	/**
	 * 角色名称
	 */
	@NotBlank(message = "角色名称不能为空")
	private String name;

	/**
	 * 角色类型
	 */
	@NotNull(message = "角色类型不能为空")
	private RoleType type;

	/**
	 * 角色描述
	 */
	private String description;
}
