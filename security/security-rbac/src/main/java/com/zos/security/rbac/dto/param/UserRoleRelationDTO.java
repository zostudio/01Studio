package com.zos.security.rbac.dto.param;

import com.zos.security.rbac.dto.RoleDTO;
import com.zos.security.rbac.dto.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleRelationDTO {
	
	private UserDTO userDTO;
	
	private List<RoleDTO> roleDTOs;
	
	private RoleDTO roleDTO;
	
	private List<UserDTO> userDTOs;
}
