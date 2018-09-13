package com.zos.security.rbac.bo.resopnse.detail;

import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleGroupDetailBO extends RoleGroupBaseBO {

	/**
	 * 创建时间
	 */
	private Date createdDate;

	/**
	 * 创建人员
	 */
	private String createdBy;

	/**
	 * 修改时间
	 */
	private Date lastModifiedDate;

	/**
	 * 修改人员
	 */
	private String lastModifiedBy;
}
