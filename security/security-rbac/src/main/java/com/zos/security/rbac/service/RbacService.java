package com.zos.security.rbac.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.zos.security.rbac.domain.User;

/**
 * 基于角色的用户权限管理核心服务
 * 
 * @author 01Studio
 *
 */
public interface RbacService {
	
	public boolean hasPermission(HttpServletRequest request, Authentication authentication);
	
	public boolean refreshCachePermission(HttpServletRequest request, Object principal);
	
	public boolean matchUrl(HttpServletRequest request, Set<User.UrlCache> userUrlCaches);
	
	public boolean hasUserRoleCache(String currentUserId);
	
	public Set<User.RoleCache> getUserRoleCaches(String currentUserId);
}
