package com.zos.security.rbac.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;

import com.zos.security.rbac.support.RequestMethod;
import com.zos.security.rbac.support.ResourceType;

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
		   @UniqueConstraint(columnNames={"url", "method"})
		})
public class Resource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1132429845275127990L;

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
	@Enumerated(EnumType.STRING)
	private ResourceType type;

    /**
     * 权限对应请求方式, 适应 Restful 风格  ALL 全部权限  GET get, POST post, PUT put, DELETE delete
     */
	@Enumerated(EnumType.STRING)
	private RequestMethod method;

	/**
	 * 序号
	 */
	private int sort;
	
	/**
     * 权限描述
     */
	private String description;

	/**
	 * 父资源
	 */
	@ManyToOne
	private Resource parent;

	/**
	 * 子资源
	 */
	@OrderBy("sort ASC")
	@OneToMany(mappedBy = "parent")
	private Set<Resource> childs = new HashSet<Resource>();
	
	/**
	 * 资源的所有角色
	 */
	@OneToMany(mappedBy="resource", cascade = CascadeType.REMOVE)
	private Set<RoleResource> roleResource = new HashSet<>();
	
}
