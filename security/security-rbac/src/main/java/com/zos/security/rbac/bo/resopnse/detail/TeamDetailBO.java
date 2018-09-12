package com.zos.security.rbac.bo.resopnse.detail;

import com.zos.security.rbac.bo.resopnse.simple.TeamSimpleBO;
import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class TeamDetailBO extends TeamSimpleBO {

	/**
	 * 创建时间
	 */
	private Date createdDate;

	/**
	 * 创建人员
	 */
	private Long createdBy;

	/**
	 * 修改时间
	 */
	private Date lastModifiedDate;

	/**
	 * 修改人员
	 */
	private Long lastModifiedBy;
}
