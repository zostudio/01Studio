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
public class UserDTO {
	
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
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 手机
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 头像
	 */
	private String avatar;
	
	/**
	 * 生日
	 */
	private String dateOfBirth;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 身份
	 */
	private String identity;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 是否过期
	 */
	private Boolean accountNonExpired = true;
	
	/**
	 * 是否冻结
	 */
	private Boolean accountNonLocked = true;
	
	/**
	 * 密码是否过期
	 */
	private Boolean credentialsNonExpired = true;
	
	/**
	 * 是否删除
	 */
	private Boolean enabled = true;

    @JsonView(SimpleView.class)
	public Long getId() {
		return id;
	}

    @JsonView(SimpleView.class)
	public String getUsername() {
		return username;
	}

    @JsonView(DetailView.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	
	@JsonView(DetailView.class)
	public String getPassword() {
		return password;
	}
	
	@JsonView(SimpleView.class)
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}
	
	@JsonView(SimpleView.class)
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	
	@JsonView(SimpleView.class)
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	@JsonView(SimpleView.class)
	public Boolean getEnabled() {
		return enabled;
	}

    @JsonView(SimpleView.class)
	public String getPhone() {
		return phone;
	}

    @JsonView(SimpleView.class)
	public String getEmail() {
		return email;
	}

    @JsonView(SimpleView.class)
    public String getAvatar() {
		return avatar;
	}

    @JsonView(SimpleView.class)
    public String getDateOfBirth() {
		return dateOfBirth;
	}

    @JsonView(SimpleView.class)
    public String getAddress() {
		return address;
	}

    @JsonView(SimpleView.class)
    public String getIdentity() {
		return identity;
	}

    @JsonView(SimpleView.class)
    public String getGender() {
		return gender;
	}
}
