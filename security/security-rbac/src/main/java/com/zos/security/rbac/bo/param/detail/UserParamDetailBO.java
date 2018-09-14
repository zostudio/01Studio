package com.zos.security.rbac.bo.param.detail;

import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.support.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserParamDetailBO extends UserParamBaseBO {

	/**
	 * 身份
	 */
	private String identity;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 生日
	 */
	private String dateOfBirth;

	/**
	 * 性别
	 */
	private Gender gender;

	/**
	 * 是否过期
	 */
	private Boolean accountNonExpired;

	/**
	 * 是否冻结
	 */
	private Boolean accountNonLocked;

	/**
	 * 密码是否过期
	 */
	private Boolean credentialsNonExpired;

	/**
	 * 是否删除
	 */
	private Boolean enabled;

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
