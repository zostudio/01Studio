package com.zos.security.rbac.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserRoleRelationDTO {
	
	private UserDTO userDTO;
	
	private List<RoleDTO> roleDTOs;
	
	private RoleDTO roleDTO;
	
	private List<UserDTO> userDTOs;
}
