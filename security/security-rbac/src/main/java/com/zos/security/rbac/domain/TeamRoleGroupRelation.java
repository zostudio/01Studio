package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.jpa.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={
	@UniqueConstraint(columnNames={"team_id", "role_group_id"})
})
public class TeamRoleGroupRelation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5925725212854896650L;
	
	/**
	 * 团队
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Team team;
	
	/**
	 * 角色组
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private RoleGroup roleGroup;
}
