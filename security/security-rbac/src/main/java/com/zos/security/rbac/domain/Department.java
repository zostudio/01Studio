package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 91927843638913597L;
	
	/**
	 * 部门名称
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * 描述
	 */
	@Column(nullable = true)
	private String description;
	
	/**
	 * 上级部门
	 */
	@ManyToOne
	private Department parent;

	/**
	 * 下级部门
	 */
	@OneToMany(mappedBy = "parent")
	private Set<Department> children = new HashSet<Department>();
	
	/**
	 * 部门的所有用户
	 */
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<DepartmentUserRelation> departmentUserRelations = new HashSet<DepartmentUserRelation>();
	
	/**
	 * 部门的所有角色
	 */
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<DepartmentRoleRelation> departmentRoleRelations = new HashSet<DepartmentRoleRelation>();
	
	/**
	 * 部门的所有角色组
	 */
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<DepartmentRoleGroupRelation> departmentRoleGroupRelations = new HashSet<DepartmentRoleGroupRelation>();
	
}
