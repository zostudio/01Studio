package com.zos.security.rbac.dto.response.base;

import com.zos.security.rbac.support.enums.RequestMethod;
import com.zos.security.rbac.support.enums.ResourceType;
import com.zos.security.rbac.validator.constraints.NotAllowValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ApiModel(value = "资源基本信息")
public class ResourceBaseDTO {

	/**
	 * 数据库表主键
	 */
	@ApiModelProperty(value = "主键")
	@NotAllowValue(message = "禁止操作主键")
	private String id;

	/**
	 * 资源名称, 如xx菜单, xx按钮
	 */
	@ApiModelProperty(value = "资源名称")
	@NotEmpty(message = "资源名称不能为空")
	private String name;

	/**
	 * 资源链接
	 */
	@ApiModelProperty(value = "资源链接")
	private String url;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标")
	private String icon;

	/**
	 * 资源类型
	 */
	@ApiModelProperty(value = "资源类型")
	private ResourceType type;

	/**
	 * 请求方式, 适用 Restful, ALL all  GET get, POST post, PUT put, DELETE delete
	 */
	@ApiModelProperty(value = "请求方式")
	private RequestMethod method;

	/**
	 * 权限描述
	 */
	@ApiModelProperty(value = "权限描述")
	private String description;
}
