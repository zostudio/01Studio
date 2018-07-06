package com.zos.security.rbac.bo;

import java.util.Date;

import lombok.Data;

@Data
public class UserBO {
	
	/**
	 * 数据库表主键
	 */
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;

	/**
	 * 用户名
	 */
	private String username;

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
