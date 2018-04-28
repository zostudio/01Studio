package com.zos.security.rbac.bo;

import java.util.List;

import lombok.Data;

@Data
public class UserRoleRelationBO {
	
	private UserBO userBO;
	
	private List<RoleBO> roleBOs;
	
	private RoleBO roleBO;
	
	private List<UserBO> userBOs;
}
