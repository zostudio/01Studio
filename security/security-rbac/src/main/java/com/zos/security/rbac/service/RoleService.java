package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.bo.RoleConditionBO;

public interface RoleService {
	
	public RoleBO create(RoleBO roleBO);
	
	public RoleBO update(String id, RoleBO roleBO);
	
	public void delete(String id);
	
	public RoleBO getInfo(String id);
	
	public Page<RoleBO> query(RoleConditionBO roleConditionBO, Pageable pageable);
}
