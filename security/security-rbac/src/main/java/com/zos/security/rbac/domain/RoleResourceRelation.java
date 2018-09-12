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
		   @UniqueConstraint(columnNames={"role_id", "resource_id"})
		})
public class RoleResourceRelation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198225982043090608L;
	
	/**
	 * 角色
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;
	
	/**
	 * 资源
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Resource resource;

}
