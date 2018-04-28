package com.zos.security.rbac.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={
		   @UniqueConstraint(columnNames={"role_id", "user_id"})
		})
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5105961540190021027L;

	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	
	/**
	 * 角色
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;
}
