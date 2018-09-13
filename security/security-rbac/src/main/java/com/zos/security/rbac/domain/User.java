package com.zos.security.rbac.domain;

import com.zos.security.rbac.support.jpa.BaseEntity;
import com.zos.security.rbac.support.enums.Gender;
import com.zos.security.rbac.support.enums.RequestMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"username"}),
		@UniqueConstraint(columnNames={"phone"}),
		@UniqueConstraint(columnNames={"email"}),
		@UniqueConstraint(columnNames={"identity"})
})
public class User extends BaseEntity implements SocialUserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1115874811024755525L;

	/**
	 * 账号
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
	@Column(nullable = false)
	private Boolean accountNonExpired = true;
	
	/**
	 * 是否冻结
	 */
	@Column(nullable = false)
	private Boolean accountNonLocked = true;
	
	/**
	 * 密码是否过期
	 */
	@Column(nullable = false)
	private Boolean credentialsNonExpired = true;
	
	/**
	 * 是否删除
	 */
	@Column(nullable = false)
	private Boolean enabled = true;

	/**
	 * 姓名
	 */
	@Column(nullable = false)
	private String nickName;

	/**
	 * 手机
	 */
	@Column(nullable = false)
	private String phone;
	
	/**
	 * 邮箱
	 */
	@Column(nullable = false)
	private String email;
	
	/**
	 * 头像
	 */
	@Column(nullable = true)
	private String avatar;
	
	/**
	 * 生日
	 */
	@Column(nullable = false)
	private String dateOfBirth;
	
	/**
	 * 地址
	 */
	@Column(nullable = false)
	private String address;
	
	/**
	 * 身份
	 */
	@Column(nullable = false)
	private String identity;
	
	/**
	 * 性别
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	/**
	 * 用户有权访问的所有接口, 不持久化到 MySQL, 缓存到 Redis 中
	 */
	@Transient
	private Set<UrlCache> urlCaches = new HashSet<UrlCache>();
	
	/**
	 * 用户的所有角色缓存, 不持久化到 MySQL, 缓存到　Redis　中
	 */
	@Transient
	private Set<RoleCache> roleCaches = new HashSet<RoleCache>();

	/**
	 * 用户的所有角色
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<UserRoleRelation> userRoleRelations = new HashSet<UserRoleRelation>();

	/**
	 * 用户的所有团队
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<TeamUserRelation> teamUserRelations = new HashSet<TeamUserRelation>();

	/**
	 * 用户的所有角色组
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<UserRoleGroupRelation> userRoleGroupRelations = new HashSet<UserRoleGroupRelation>();
	
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
		private String id;
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
		for (UserRoleRelation userRoleRelation : getUserRoleRelations()) {
			for (RoleResourceRelation roleResourceRelation : userRoleRelation.getRole().getRoleResourceRelations()) {
				consumer.accept(roleResourceRelation.getResource());
			}
		}
	}

	private void forEachRole(Consumer<Role> consumer) {
		for (UserRoleRelation userRoleRelation : getUserRoleRelations()) {
			consumer.accept(userRoleRelation.getRole());
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
