package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.ResourceBO;
import com.zos.security.rbac.bo.ResourceConditionBO;

public interface ResourceService {
	
	public ResourceBO create(ResourceBO resourceBO);
	
	public ResourceBO update(ResourceBO resourceBO);
	
	public void delete(Long id);
	
	public ResourceBO getInfo(Long id);
	
	public Page<ResourceBO> query(ResourceConditionBO resourceConditionBO, Pageable pageable);
}
