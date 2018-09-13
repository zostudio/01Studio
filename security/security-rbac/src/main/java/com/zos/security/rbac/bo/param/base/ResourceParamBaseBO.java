package com.zos.security.rbac.bo.param.base;

import com.zos.security.rbac.support.enums.RequestMethod;
import com.zos.security.rbac.support.enums.ResourceType;
import lombok.Data;

@Data
public class ResourceParamBaseBO {

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
	 * 请求方式, 适用 Restful, ALL all  GET get, POST post, PUT put, DELETE delete
	 */
	private RequestMethod method;

	/**
	 * 权限描述
	 */
	private String description;
}
