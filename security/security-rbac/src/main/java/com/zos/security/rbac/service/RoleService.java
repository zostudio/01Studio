package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.bo.RoleConditionBO;

public interface RoleService {
	
	public RoleBO create(RoleBO roleBO);
	
	public RoleBO update(RoleBO roleBO);
	
	public void delete(Long id);
	
	public RoleBO getInfo(Long id);
	
	public Page<RoleBO> query(RoleConditionBO roleConditionBO, Pageable pageable);
}
