package com.zos.security.rbac.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 91927843638913597L;

	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/**
	 * 序号
	 */
	private Integer sort;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 上级部门
	 */
	@ManyToOne
	private Department parent;

	/**
	 * 下级部门
	 */
	@OrderBy("sort ASC")
	@OneToMany(mappedBy = "parent")
	private Set<Department> childs = new HashSet<Department>();
	
	@OneToMany(mappedBy="department", cascade = CascadeType.REMOVE)
	private Set<DepartmentUser> departmentUser = new HashSet<DepartmentUser>();
	
}
