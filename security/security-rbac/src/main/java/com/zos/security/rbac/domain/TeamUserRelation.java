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
		   @UniqueConstraint(columnNames={"department_id", "user_id"})
		})
public class TeamUserRelation extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4841913369024266641L;
	
	/**
	 * 团队
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Team department;
	
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
}
