package com.zos.security.rbac.dto;

import com.zos.security.rbac.support.enums.Gender;
import lombok.Data;

@Data
public class UserDTO {
	
	/**
	 * 数据库表主键
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	private String nickName;

	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 手机
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 头像
	 */
	private String avatar;
	
	/**
	 * 生日
	 */
	private String dateOfBirth;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 身份
	 */
	private String identity;
	
	/**
	 * 性别
	 */
	private Gender gender;

	/**
	 * 是否过期
	 */
	private Boolean accountNonExpired = true;

	/**
	 * 是否冻结
	 */
	private Boolean accountNonLocked = true;

	/**
	 * 密码是否过期
	 */
	private Boolean credentialsNonExpired = true;

	/**
	 * 是否删除
	 */
	private Boolean enabled = true;
}
