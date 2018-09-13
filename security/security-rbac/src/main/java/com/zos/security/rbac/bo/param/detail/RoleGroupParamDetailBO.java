package com.zos.security.rbac.bo.param.detail;

import com.zos.security.rbac.bo.param.base.RoleGroupParamBaseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleGroupParamDetailBO extends RoleGroupParamBaseBO {

	/**
	 * 创建时间开始
	 */
	private Date createdDateStart;

	/**
	 * 创建时间结束
	 */
	private Date createdDateEnd;


	/**
	 * 创建人员
	 */
	private String createdBy;

	/**
	 * 修改时间开始
	 */
	private Date lastModifiedDateStart;

	/**
	 * 修改时间结束
	 */
	private Date lastModifiedDateEnd;

	/**
	 * 修改人员
	 */
	private String lastModifiedBy;
}
