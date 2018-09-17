package com.zos.security.rbac.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zos.security.rbac.support.enums.Gender;
import com.zos.security.rbac.support.enums.RequestMethod;
import com.zos.security.rbac.support.enums.ResourceType;
import com.zos.security.rbac.support.enums.RoleType;
import com.zos.security.rbac.support.jpa.BaseEntity;
import com.zos.security.rbac.utils.ConstantValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.io.Serializable;
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
	 * 证件
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
	 * 用户有权访问的所有接口, 不持久化到 MySQL, 缓存到 Redis 中
	 */
	@Transient
    @JsonProperty(value = "urlCaches")
	private Set<UrlCache> urlCaches = new HashSet<UrlCache>();
	
	/**
	 * 用户的所有角色缓存, 不持久化到 MySQL, 缓存到　Redis　中
	 */
	@Transient
    @JsonProperty(value = "roleCaches")
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
	public static class UrlCache implements Serializable {
        @JsonProperty(value = "id")
        private String id;
        @JsonProperty(value = "name")
        private String name;
        @JsonProperty(value = "url")
		private String url;
        @JsonProperty(value = "icon")
		private String icon;
        @JsonProperty(value = "type")
		private ResourceType type;
        @JsonProperty(value = "method")
		private RequestMethod method;

        @Override
        public boolean equals(Object object) {
            if (ConstantValidator.isNull(object)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            if (object instanceof UrlCache) {
                UrlCache urlCache =(UrlCache)object;
                if (urlCache.id == this.id) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.id.hashCode();
        }
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RoleCache implements Serializable {
        @JsonProperty(value = "id")
		private String id;
        @JsonProperty(value = "name")
		private String name;
        @JsonProperty(value = "roleType")
		private RoleType roleType;

        @Override
        public boolean equals(Object object) {
            if (ConstantValidator.isNull(object)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            if (object instanceof RoleCache) {
                RoleCache roleCache =(RoleCache)object;
                if (roleCache.id == this.id) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.id.hashCode();
        }
	}
	
	public Set<UrlCache> getUrlCaches() {
		init(urlCaches);
		forEachResource(resource -> {
			UrlCache url = new UrlCache(resource.getId(), resource.getName(), resource.getUrl(), resource.getIcon(), resource.getType(), resource.getMethod());
			urlCaches.add(url);
		});
		return urlCaches;
	}
	
	public Set<RoleCache> getRoleCaches() {
		init(roleCaches);
		forEachRole(role -> {
			RoleCache roleCache = new RoleCache(role.getId(), role.getName(), role.getType());
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
        if (CollectionUtils.isNotEmpty(getUserRoleRelations())) {
            // 账号关联角色
            for (UserRoleRelation userRoleRelation : getUserRoleRelations()) {
                // 角色关联资源
                if (ConstantValidator.isNotNull(userRoleRelation.getRole()) && CollectionUtils.isNotEmpty(userRoleRelation.getRole().getRoleResourceRelations())) {
                    for (RoleResourceRelation roleResourceRelation : userRoleRelation.getRole().getRoleResourceRelations()) {
                        consumer.accept(roleResourceRelation.getResource());
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(getUserRoleGroupRelations())) {
            // 账号关联角色组
            for (UserRoleGroupRelation userRoleGroupRelation : getUserRoleGroupRelations()) {
                // 角色组关联角色
                if (ConstantValidator.isNotNull(userRoleGroupRelation.getRoleGroup()) && CollectionUtils.isNotEmpty(userRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations())) {
                    for (RoleGroupRoleRelation roleGroupRoleRelation : userRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations()) {
                        // 角色关联资源
                        if (ConstantValidator.isNotNull(roleGroupRoleRelation.getRole()) && CollectionUtils.isNotEmpty(roleGroupRoleRelation.getRole().getRoleResourceRelations())) {
                            for (RoleResourceRelation roleResourceRelation : roleGroupRoleRelation.getRole().getRoleResourceRelations()) {
                                consumer.accept(roleResourceRelation.getResource());
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(getTeamUserRelations())) {
            // 账号关联团队
            for(TeamUserRelation teamUserRelation : getTeamUserRelations()) {
                if (ConstantValidator.isNotNull(teamUserRelation.getTeam()) && CollectionUtils.isNotEmpty(teamUserRelation.getTeam().getTeamRoleGroupRelations())) {
                    // 团队关联角色组
                    for (TeamRoleGroupRelation teamRoleGroupRelation : teamUserRelation.getTeam().getTeamRoleGroupRelations()) {
                        if (ConstantValidator.isNotNull(teamRoleGroupRelation.getRoleGroup()) && CollectionUtils.isNotEmpty(teamRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations())) {
                            // 角色组关联角色
                            for (RoleGroupRoleRelation roleGroupRoleRelation : teamRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations()) {
                                if (ConstantValidator.isNotNull(roleGroupRoleRelation.getRole()) && CollectionUtils.isNotEmpty(roleGroupRoleRelation.getRole().getRoleResourceRelations())) {
                                    // 角色关联资源
                                    for (RoleResourceRelation roleResourceRelation : roleGroupRoleRelation.getRole().getRoleResourceRelations()) {
                                        consumer.accept(roleResourceRelation.getResource());
                                    }
                                }
                            }
                        }
                    }
                }
                if (ConstantValidator.isNotNull(teamUserRelation.getTeam()) && CollectionUtils.isNotEmpty(teamUserRelation.getTeam().getTeamRoleRelations())) {
                    // 团队关联角色
                    for (TeamRoleRelation teamRoleRelation : teamUserRelation.getTeam().getTeamRoleRelations()) {
                        if (ConstantValidator.isNotNull(teamRoleRelation.getRole()) && CollectionUtils.isNotEmpty(teamRoleRelation.getRole().getRoleResourceRelations())) {
                            // 角色关联资源
                            for (RoleResourceRelation roleResourceRelation : teamRoleRelation.getRole().getRoleResourceRelations()) {
                                consumer.accept(roleResourceRelation.getResource());
                            }
                        }
                    }
                }
            }
        }
	}

	private void forEachRole(Consumer<Role> consumer) {
        if (CollectionUtils.isNotEmpty(getUserRoleRelations())) {
            // 账号关联角色
            for (UserRoleRelation userRoleRelation : getUserRoleRelations()) {
                consumer.accept(userRoleRelation.getRole());
            }
        }
        if (CollectionUtils.isNotEmpty(getUserRoleGroupRelations())) {
            // 账号关联角色组
            for (UserRoleGroupRelation userRoleGroupRelation : getUserRoleGroupRelations()) {
                // 角色组关联角色
                if (ConstantValidator.isNotNull(userRoleGroupRelation.getRoleGroup()) && CollectionUtils.isNotEmpty(userRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations())) {
                    for (RoleGroupRoleRelation roleGroupRoleRelation : userRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations()) {
                        consumer.accept(roleGroupRoleRelation.getRole());
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(getTeamUserRelations())) {
            // 账号关联团队
            for(TeamUserRelation teamUserRelation : getTeamUserRelations()) {
                if (ConstantValidator.isNotNull(teamUserRelation.getTeam()) && CollectionUtils.isNotEmpty(teamUserRelation.getTeam().getTeamRoleGroupRelations())) {
                    // 团队关联角色组
                    for (TeamRoleGroupRelation teamRoleGroupRelation : teamUserRelation.getTeam().getTeamRoleGroupRelations()) {
                        if (ConstantValidator.isNotNull(teamRoleGroupRelation.getRoleGroup()) && CollectionUtils.isNotEmpty(teamRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations())) {
                            // 角色组关联角色
                            for (RoleGroupRoleRelation roleGroupRoleRelation : teamRoleGroupRelation.getRoleGroup().getRoleGroupRoleRelations()) {
                                consumer.accept(roleGroupRoleRelation.getRole());
                            }
                        }
                    }
                }
                if (ConstantValidator.isNotNull(teamUserRelation.getTeam()) && CollectionUtils.isNotEmpty(teamUserRelation.getTeam().getTeamRoleRelations())) {
                    // 团队关联角色
                    for (TeamRoleRelation teamRoleRelation : teamUserRelation.getTeam().getTeamRoleRelations()) {
                        consumer.accept(teamRoleRelation.getRole());
                    }
                }
            }
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
