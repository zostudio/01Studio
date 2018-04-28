package com.zos.security.rbac.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserConditionBO;
import com.zos.security.rbac.bo.UserRoleBO;
import com.zos.security.rbac.bo.UserRoleRelationBO;

public interface UserService {

	public UserBO create(UserBO userBO);
	
	public UserBO update(UserBO userBO);
	
	public void delete(Long id);
	
	public UserBO getInfo(Long id);
	
	public UserBO findByUsername(String username);
	
	public Page<UserBO> query(UserConditionBO userConditionBO, Pageable pageable);
	
	public List<UserRoleBO> addRoles(UserRoleRelationBO userRoleRelationBO);
	
	public void delRoles(Long id, UserRoleRelationBO userRoleRelationBO);
}
