package com.zos.security.rbac.dto.response.detail;

import com.zos.security.rbac.dto.response.base.ResourceBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "资源详细信息")
@EqualsAndHashCode(callSuper=true)
public class ResourceDetailDTO extends ResourceBaseDTO {

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
