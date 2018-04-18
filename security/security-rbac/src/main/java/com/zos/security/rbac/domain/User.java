package com.zos.security.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1115874811024755525L;

	/**
	 * 数据库主键
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
	 * 用户名
	 */
	@Column(nullable = false)
	private String username;

	/**
	 * 密码
	 */
	@Column(nullable = false)
	private String password;
	
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

	/**
	 * 用户的所有角色
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
}
