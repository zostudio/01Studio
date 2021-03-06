package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.jpa.BaseEntity;
import com.zos.security.rbac.support.enums.RequestMethod;
import com.zos.security.rbac.support.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={
		   @UniqueConstraint(columnNames={"name", "url", "type", "method"})
		})
public class Resource extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1132429845275127990L;

	/**
	 * 资源名称, 如xx菜单, xx按钮
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 资源链接
	 */
	@Column(nullable = true)
	private String url;

	/**
	 * 图标
	 */
	@Column(nullable = true)
	private String icon;

	/**
	 * 资源类型
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ResourceType type;

    /**
     * 请求方式, 适用 Restful, ALL all  GET get, POST post, PUT put, DELETE delete
     */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestMethod method;

	/**
	 * 资源描述
	 */
	@Column(nullable = true)
	private String description;

	/**
	 * 父资源
	 */
	@ManyToOne
	private Resource parent;

	/**
	 * 子资源
	 */
	@OneToMany(mappedBy = "parent")
	private Set<Resource> children = new HashSet<Resource>();
	
	/**
	 * 资源的所有角色
	 */
	@OneToMany(mappedBy="resource", cascade = CascadeType.REMOVE)
	private Set<RoleResourceRelation> roleResourceRelations = new HashSet<>();
	
}
