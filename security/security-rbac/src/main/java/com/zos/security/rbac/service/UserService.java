package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserConditionBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	public UserBO create(UserBO userBO);
	
	public UserBO update(String id, UserBO userBO);
	
	public void delete(String id);
	
	public UserBO getInfo(String id);
	
	public UserBO findByUsername(String username);
	
	public UserBO findByEmail(String email);
	
	public UserBO findByPhone(String phone);
	
	public Page<UserBO> query(UserConditionBO userConditionBO, Pageable pageable);
	
//	public List<UserRoleBO> addRoles(UserRoleRelationBO userRoleRelationBO);
//
//	public void delRoles(String id, UserRoleRelationBO userRoleRelationBO);
	
	public Long updatePwd(String id, UserConditionBO userConditionBO);
}
