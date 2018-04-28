package com.zos.security.rbac.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -975461664568511798L;

	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * 角色名称
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 角色的所有资源
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private Set<RoleResource> roleResources  = new HashSet<RoleResource>();

	/**
	 * 角色的所有用户
	 */
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	/**
	 * 角色的所有部门
	 */
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)
	private Set<DepartmentRole> departmentRoles = new HashSet<DepartmentRole>();

	/**
	 * 角色的所有角色组
	 */
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)
	private Set<RoleGroupRole> roleGroupRoles = new HashSet<RoleGroupRole>();
	
}
