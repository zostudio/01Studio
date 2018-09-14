package com.zos.security.rbac.bo.param.base;

import lombok.Data;

@Data
public class UserParamBaseBO {

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 姓名
	 */
	private String nickName;

	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;
}
