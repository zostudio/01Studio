package com.zos.security.rbac.dto.response.base;

import com.zos.security.rbac.validator.constraints.NotAllowValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "账号基本信息")
public class UserBaseDTO {

	/**
	 * 数据库表主键
	 */
	@ApiModelProperty(value = "主键")
	@NotAllowValue(message = "禁止操作主键")
	private String id;

	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	@NotAllowValue(message = "禁止操作账号")
	private String username;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String nickName;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	@NotAllowValue(message = "禁止操作头像")
	private String avatar;

	/**
	 * 手机
	 */
	@ApiModelProperty(value = "手机")
	private String phone;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
}
