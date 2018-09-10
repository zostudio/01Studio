package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.BaseEntity;
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
		@UniqueConstraint(columnNames={"role_id", "role_group_id"})
})
public class RoleGroupRoleRelation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5925725212854896650L;
	
	/**
	 * 角色
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;
	
	/**
	 * 角色组
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private RoleGroup roleGroup;
}
