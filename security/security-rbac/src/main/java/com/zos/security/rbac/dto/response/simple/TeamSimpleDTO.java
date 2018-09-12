package com.zos.security.rbac.dto.response.simple;

import com.zos.security.rbac.validator.constraints.NotAllowValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ApiModel(value = "团队基本信息")
public class TeamSimpleDTO {

	/**
	 * 数据库表主键
	 */
	@ApiModelProperty(value = "主键")
	@NotAllowValue(message = "禁止操作主键")
	private Long id;
	
	/**
	 * 团队名称
	 */
	@ApiModelProperty(value = "团队名称")
	private String name;
	
	/**
	 * 团队描述
	 */
	@ApiModelProperty(value = "团队描述")
	private String description;
}
