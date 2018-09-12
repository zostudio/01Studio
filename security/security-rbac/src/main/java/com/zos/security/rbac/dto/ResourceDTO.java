package com.zos.security.rbac.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zos.security.rbac.support.enums.RequestMethod;
import com.zos.security.rbac.support.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO {
	
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
	 * 资源名称, 如xx菜单, xx按钮
	 */
	private String name;

	/**
	 * 资源链接
	 */
	private String url;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 资源类型
	 */
	private ResourceType type;

    /**
     * 权限对应请求方式, 适应 Restful 风格  ALL 全部权限  GET get, POST post, PUT put, DELETE delete
     */
	private RequestMethod method;

	/**
	 * 序号
	 */
	private int sort;
	
	/**
     * 权限描述
     */
	private String description;
	
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
	
	@JsonView(SimpleView.class)
	public String getUrl() {
		return url;
	}
	
	@JsonView(SimpleView.class)
	public String getIcon() {
		return icon;
	}
	
	@JsonView(SimpleView.class)
	public ResourceType getType() {
		return type;
	}
	
	@JsonView(SimpleView.class)
	public RequestMethod getMethod() {
		return method;
	}
	
	@JsonView(SimpleView.class)
	public int getSort() {
		return sort;
	}
	
	@JsonView(DetailView.class)
	public String getDescription() {
		return description;
	}
}
