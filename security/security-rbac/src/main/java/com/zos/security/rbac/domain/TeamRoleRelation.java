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
   @UniqueConstraint(columnNames={"team_id", "role_id"})
})
public class TeamRoleRelation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2907574495545743490L;
	
	/**
	 * 团队
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Team team;
	
	/**
	 * 角色
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;
}
