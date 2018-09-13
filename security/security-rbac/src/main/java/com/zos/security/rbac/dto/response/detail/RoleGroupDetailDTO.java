package com.zos.security.rbac.dto.response.detail;

import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value = "角色组详细信息")
public class RoleGroupDetailDTO extends RoleGroupBaseDTO {

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createdDate;

	/**
	 * 创建人员
	 */
	@ApiModelProperty(value = "创建人员")
	private String createdBy;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date lastModifiedDate;

	/**
	 * 修改人员
	 */
	@ApiModelProperty(value = "修改人员")
	private String lastModifiedBy;
}
