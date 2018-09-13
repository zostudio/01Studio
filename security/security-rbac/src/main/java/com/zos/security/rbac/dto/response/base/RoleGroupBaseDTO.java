package com.zos.security.rbac.dto.response.base;

import com.zos.security.rbac.support.enums.RoleType;
import com.zos.security.rbac.validator.constraints.NotAllowValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "角色组基本信息")
public class RoleGroupBaseDTO {

	/**
	 * 数据库表主键
	 */
	@ApiModelProperty(value = "主键")
	@NotAllowValue(message = "禁止操作主键")
	private String id;
	
	/**
	 * 角色组名称
	 */
	@ApiModelProperty(value = "角色组名称")
	@NotEmpty(message = "角色组名称不能为空")
	private String name;

	/**
	 * 角色类型
	 */
	@ApiModelProperty(value = "角色类型")
	@NotNull(message = "角色类型不能为空")
	private RoleType type;
	
	/**
	 * 角色组描述
	 */
	@ApiModelProperty(value = "角色组描述")
	private String description;
}
