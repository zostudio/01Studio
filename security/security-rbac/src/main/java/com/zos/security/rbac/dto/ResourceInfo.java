package com.zos.security.rbac.dto;

import java.util.ArrayList;
import java.util.List;

import com.zos.security.rbac.domain.ResourceType;
import lombok.Data;

/**
 * @author 01Studio
 *
 */
@Data
public class ResourceInfo {
	
	/**
	 * 资源ID
	 */
	private Long id;

	/**
	 * 
	 */
	private Long parentId;

	/**
	 * 资源名
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
	private ResourceType type;

	/**
	 * 子节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

}
