package com.zos.security.rbac.bo.resopnse.base;

import lombok.Data;

@Data
public class UserBaseBO {

	/**
	 * 数据库表主键
	 */
	private String id;

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 姓名
	 */
	private String nickName;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;
}
