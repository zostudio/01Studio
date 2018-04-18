package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserConditionBO;

public interface UserService {

	public UserBO create(UserBO userBO);
	
	public UserBO update(UserBO userBO);
	
	public void delete(Long id);
	
	public UserBO getInfo(Long id);
	
	public Page<UserBO> query(UserConditionBO userConditionBO, Pageable pageable);
}
