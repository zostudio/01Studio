package com.zos.security.rbac.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;

import com.zos.security.rbac.dto.ResourceInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 需要控制权限的资源, 以业务人员能看懂的 name 呈现, 实际关联到一个或多个url上, 树形结构
 * 
 * @author 01Studio
 *
 */
@Getter
@Setter
@Entity
public class Resource {

	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 审计日志, 记录条目创建时间, 自动赋值, 不需要程序员手工赋值
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdTime;

	/**
	 * 资源名称, 如xx菜单, xx按钮
	 */
	private String name;

	/**
	 * 资源链接
	 */
	private String link;

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
	 * 实际需要控制权限的url
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> urls;

	/**
	 * 父资源
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Resource parent;

	/**
	 * 子资源
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	@OrderBy("sort ASC")
	private List<Resource> childs = new ArrayList<>();

	/**
	 * 序号
	 */
	private int sort;

	public ResourceInfo toTree(Admin admin) {
		ResourceInfo result = new ResourceInfo();
		BeanUtils.copyProperties(this, result);
		Set<Long> resourceIds = admin.getAllResourceIds();
		
		List<ResourceInfo> children = new ArrayList<ResourceInfo>();
		for (Resource child : getChilds()) {
			if(StringUtils.equals(admin.getUsername(), "admin") || 
					resourceIds.contains(child.getId())){
				children.add(child.toTree(admin));
			}
		}
		result.setChildren(children);
		return result;
	}
	
	public void addChild(Resource child) {
		childs.add(child);
		child.setParent(this);
	}

}
