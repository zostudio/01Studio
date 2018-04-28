package com.zos.security.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.zos.security.rbac.support.RequestMethod;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements SocialUserDetails {
	
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
	 * 用户有权访问的所有接口, 不持久化到 MySQL, 缓存到　Redis　中
	 */
	@Transient
	private Set<UrlCache> urlCaches = new HashSet<UrlCache>();
	
	/**
	 * 用户的所有角色缓存, 不持久化到 MySQL, 缓存到　Redis　中
	 */
	@Transient
	private Set<RoleCache> roleCaches = new HashSet<RoleCache>();
	
	@Transient
	private String userId;

	/**
	 * 用户的所有角色
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	/**
	 * 用户的所有部门
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<DepartmentUser> departmentUsers = new HashSet<DepartmentUser>();

	/**
	 * 用户的所有角色组
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<UserRoleGroup> userRoleGroups = new HashSet<UserRoleGroup>();
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class UrlCache {
		private String url;
		private RequestMethod method;
		
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class RoleCache {
		private Long id;
		private String name;
		
	}
	
	public Set<UrlCache> getUrls() {
		init(urlCaches);
		forEachResource(resource -> {
			UrlCache url = new UrlCache(resource.getUrl(), resource.getMethod());
			urlCaches.add(url);
		});
		return urlCaches;
	}
	
	public Set<RoleCache> getRoleCaches() {
		init(roleCaches);
		forEachRole(role -> {
			RoleCache roleCache = new RoleCache(role.getId(), role.getName());
			roleCaches.add(roleCache);
		});
		return roleCaches;
	}
	
	private void init(Set<?> data){
		if (CollectionUtils.isEmpty(data)) {
			if (data == null) {
				data = new HashSet<>();
			}
		}
	}

	private void forEachResource(Consumer<Resource> consumer) {
		for (UserRole userRole : getUserRoles()) {
			for (RoleResource roleResource : userRole.getRole().getRoleResources()) {
				consumer.accept(roleResource.getResource());
			}
		}
	}

	private void forEachRole(Consumer<Role> consumer) {
		for (UserRole userRole : getUserRoles()) {
			consumer.accept(userRole.getRole());
		}
	}

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

	@Override
	public String getUserId() {
		return this.username;
	}
	
}
