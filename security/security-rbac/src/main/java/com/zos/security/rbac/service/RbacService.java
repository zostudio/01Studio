package com.zos.security.rbac.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * 基于角色的用户权限管理核心服务
 * 
 * @author 01Studio
 *
 */
public interface RbacService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);
	
}
