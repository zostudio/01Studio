package com.zos.security.rbac.dto.response.detail;

import com.zos.security.rbac.dto.response.simple.TeamSimpleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value = "团队详细信息")
public class TeamDetailDTO extends TeamSimpleDTO {

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createdDate;

	/**
	 * 创建人员
	 */
	@ApiModelProperty(value = "创建人员")
	private Long createdBy;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date lastModifiedDate;

	/**
	 * 修改人员
	 */
	@ApiModelProperty(value = "修改人员")
	private Long lastModifiedBy;
}
