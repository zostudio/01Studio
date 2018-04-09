package com.zos.security.rbac.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;

/**
 * 角色用户关系表
 * 
 * @author 01Studio
 *
 */
@Getter
@Setter
@Entity
public class RoleAdmin {

	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值, 不需要程序员手工赋值
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdTime;

	/**
	 * 角色
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	/**
	 * 管理员
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Admin admin;

}
