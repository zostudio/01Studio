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
public class RoleDTO {
	
	public interface SimpleView {};
	public interface DetailView extends SimpleView {};

	/**
	 * 数据库表主键
	 */
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值
	 */
	private Date createdDate;

	/**
	 * 角色名称
	 */
	private String name;
	
	@JsonView(SimpleView.class)
	public Long getId() {
		return id;
	}
	
	@JsonView(DetailView.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	
	@JsonView(SimpleView.class)
	public String getName() {
		return name;
	}
}
