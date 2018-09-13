package com.zos.security.rbac.dto.response.base;

import com.zos.security.rbac.validator.constraints.NotAllowValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ApiModel(value = "团队基本信息")
public class TeamBaseDTO {

	/**
	 * 数据库表主键
	 */
	@ApiModelProperty(value = "主键")
	@NotAllowValue(message = "禁止操作主键")
	private String id;
	
	/**
	 * 团队名称
	 */
	@ApiModelProperty(value = "团队名称")
	@NotEmpty(message = "团队名称不能为空")
	private String name;
	
	/**
	 * 团队描述
	 */
	@ApiModelProperty(value = "团队描述")
	private String description;
}
