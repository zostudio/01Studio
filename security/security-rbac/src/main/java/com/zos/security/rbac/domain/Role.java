package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.BaseEntity;
import com.zos.security.rbac.support.RoleType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"name", "type"})
})
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
	@Enumerated(EnumType.STRING)
	private RoleType type;

	/**
	 * 角色描述
	 */
	@Column(nullable = true)
	private String description;

	/**
	 * 角色的所有资源
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private Set<RoleResourceRelation> roleResourceRelations  = new HashSet<RoleResourceRelation>();

	/**
	 * 角色的所有用户
	 */
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)
	private Set<UserRoleRelation> userRoleRelations = new HashSet<UserRoleRelation>();

	/**
	 * 角色的所有部门
	 */
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)
	private Set<DepartmentRoleRelation> departmentRoleRelations = new HashSet<DepartmentRoleRelation>();

	/**
	 * 角色的所有角色组
	 */
	@OneToMany(mappedBy="role", cascade = CascadeType.REMOVE)
	private Set<RoleGroupRoleRelation> roleGroupRoleRelations = new HashSet<RoleGroupRoleRelation>();
	
}
