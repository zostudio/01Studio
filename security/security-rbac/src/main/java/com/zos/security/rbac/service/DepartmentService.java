package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.DepartmentBO;
import com.zos.security.rbac.bo.DepartmentConditionBO;

public interface DepartmentService {
	
	public DepartmentBO create(DepartmentBO departmentBO);
	
	public DepartmentBO update(DepartmentBO departmentBO);
	
	public void delete(Long id);
	
	public DepartmentBO getInfo(Long id);
	
	public Page<DepartmentBO> query(DepartmentConditionBO departmentConditionBO, Pageable pageable);
}
