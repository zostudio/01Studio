package com.zos.security.rbac.bo.resopnse.info;

import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.support.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserInfoBO extends UserBaseBO {

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
}
