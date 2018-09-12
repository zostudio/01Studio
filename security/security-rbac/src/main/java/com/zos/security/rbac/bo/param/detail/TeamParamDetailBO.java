package com.zos.security.rbac.bo.param.detail;

import com.zos.security.rbac.bo.param.simple.TeamParamSimpleBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class TeamParamDetailBO extends TeamParamSimpleBO{

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
	private Long createdBy;

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
	private Long lastModifiedBy;
}
