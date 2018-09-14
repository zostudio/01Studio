package com.zos.security.rbac.bo.resopnse.detail;

import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.support.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserDetailBO extends UserBaseBO {

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
