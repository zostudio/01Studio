package com.zos.security.rbac.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
	
	public interface SimpleView {};
	public interface DetailView extends SimpleView {};
	
	/**
	 * 数据库表主键
	 */
	private Long id;
	
	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;
	
	/**
	 * 序号
	 */
	private Integer sort;
	
	/**
	 * 描述
	 */
	private String description;
	
	@JsonView(SimpleView.class)
	public Long getId() {
		return id;
	}
	
	@JsonView(SimpleView.class)
	public String getName() {
		return name;
	}
	
	@JsonView(DetailView.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	
	@JsonView(SimpleView.class)
	public Integer getSort() {
		return sort;
	}
	
	@JsonView(DetailView.class)
	public String getDescription() {
		return description;
	}
}
