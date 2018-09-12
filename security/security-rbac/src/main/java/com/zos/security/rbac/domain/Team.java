package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.jpa.BaseEntity;
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
public class Team extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 91927843638913597L;
	
	/**
	 * 团队名称
	 */
	@Column(nullable = false)
	private String name;
	
	/**
	 * 描述
	 */
	@Column(nullable = true)
	private String description;
	
	/**
	 * 上级团队
	 */
	@ManyToOne
	private Team parent;

	/**
	 * 下级团队
	 */
	@OneToMany(mappedBy = "parent")
	private Set<Team> children = new HashSet<Team>();
	
	/**
	 * 团队的所有用户
	 */
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<TeamUserRelation> departmentUserRelations = new HashSet<TeamUserRelation>();
	
	/**
	 * 团队的所有角色
	 */
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<TeamRoleRelation> departmentRoleRelations = new HashSet<TeamRoleRelation>();
	
	/**
	 * 团队的所有角色组
	 */
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<TeamRoleGroupRelation> departmentRoleGroupRelations = new HashSet<TeamRoleGroupRelation>();
	
}
