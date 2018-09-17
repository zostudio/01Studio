package com.zos.security.rbac.dto.response.info;

import com.zos.security.rbac.support.enums.Gender;
import com.zos.security.rbac.validator.constraints.NotAllowValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "账号信息")
public class UserInfoDTO {

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
	@NotEmpty(message = "账号不能为空")
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
	private String password;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
	private String nickName;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
    @NotEmpty(message = "头像不能为空")
	private String avatar;

	/**
	 * 手机
	 */
	@ApiModelProperty(value = "手机")
    @NotEmpty(message = "手机不能为空")
	private String phone;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
    @NotEmpty(message = "邮箱不能为空")
	private String email;
	/**
	 * 证件
	 */
	@ApiModelProperty(value = "证件")
    @NotEmpty(message = "证件不能为空")
	private String identity;

	/**
	 * 地址
	 */
	@ApiModelProperty(value = "地址")
    @NotEmpty(message = "地址不能为空")
	private String address;

	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日")
    @NotEmpty(message = "生日不能为空")
	private String dateOfBirth;

	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
    @NotNull(message = "性别不能为空")
	private Gender gender;
}
