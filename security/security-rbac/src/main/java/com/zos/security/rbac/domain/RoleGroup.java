package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.jpa.BaseEntity;
import com.zos.security.rbac.support.enums.RoleType;
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
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"name", "type"})
})
public class RoleGroup extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4847483082166122608L;

	/**
	 * 角色组名称
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
	 * 角色组描述
	 */
	@Column(nullable = true)
	private String description;

	/**
	 * 父角色组
	 */
	@ManyToOne
	private RoleGroup parent;

	/**
	 * 子角色组
	 */
	@OneToMany(mappedBy = "parent")
	private Set<RoleGroup> children = new HashSet<RoleGroup>();

	/**
	 * 角色组的所有角色
	 */
	@OneToMany(mappedBy="roleGroup", cascade = CascadeType.REMOVE)
	private Set<RoleGroupRoleRelation> roleGroupRoleRelations = new HashSet<RoleGroupRoleRelation>();

	/**
	 * 角色组的所有团队
	 */
	@OneToMany(mappedBy="roleGroup", cascade = CascadeType.REMOVE)
	private Set<TeamRoleGroupRelation> teamRoleGroupRelations = new HashSet<TeamRoleGroupRelation>();

	/**
	 * 角色组的所有用户
	 */
	@OneToMany(mappedBy="roleGroup", cascade = CascadeType.REMOVE)
	private Set<UserRoleGroupRelation> userRoleGroupRelations = new HashSet<UserRoleGroupRelation>();
}
