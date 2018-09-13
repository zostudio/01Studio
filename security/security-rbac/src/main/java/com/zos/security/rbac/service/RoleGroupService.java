package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.RoleGroupBO;
import com.zos.security.rbac.bo.RoleGroupConditionBO;

public interface RoleGroupService {
	
	public RoleGroupBO create(RoleGroupBO roleGroupBO);
	
	public RoleGroupBO update(RoleGroupBO roleGroupBO);
	
	public void delete(String id);
	
	public RoleGroupBO getInfo(String id);
	
	public Page<RoleGroupBO> query(RoleGroupConditionBO roleGroupConditionBO, Pageable pageable);
}
