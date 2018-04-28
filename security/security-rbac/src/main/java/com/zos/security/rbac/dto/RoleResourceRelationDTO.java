package com.zos.security.rbac.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoleResourceRelationDTO {

	private RoleDTO roleDTO;

	private ResourceDTO resourceDTO;

	private List<RoleDTO> roleDTOs;

	private List<ResourceDTO> resourceDTOs;
	
}
