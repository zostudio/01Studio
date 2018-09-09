package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.BaseEntity;
import com.zos.security.rbac.support.RoleType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -975461664568511798L;

	/**
	 * 角色名称
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 角色类型
	 */
	@Column(nullable = false)
	private RoleType roleType;

	/**
	 * 角色描述
	 */
	@Column(nullable = true)
	private String description;

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
