package com.zos.security.rbac.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserRoleBO;
import com.zos.security.rbac.bo.UserRoleRelationBO;
import com.zos.security.rbac.domain.UserRole;
import com.zos.security.rbac.dto.RoleDTO;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.UserRoleDTO;
import com.zos.security.rbac.dto.UserRoleRelationDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {
	
	UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);
	
	RoleBO dtoToBO(RoleDTO roleDTO);
	
	List<RoleBO> dtoToBOByRole(List<RoleDTO> roleDTOs);
	
	UserBO dtoToBO(UserDTO userDTO);
	
	List<UserBO> dtoToBOByUser(List<UserDTO> userDTOs);
	
	@Mappings({
		@Mapping(source = "userDTO", target = "userBO"),
		@Mapping(source = "roleDTO", target = "roleBO"),
		@Mapping(source = "userDTOs", target = "userBOs"),
		@Mapping(source = "roleDTOs", target = "roleBOs")
	})
	UserRoleRelationBO dtoToBO(UserRoleRelationDTO userRoleRelationDTO);
	
	UserRoleBO domainToBO(UserRole userRole);
	
	UserRoleDTO boToDTO(UserRoleBO userRoleBO);
	
	List<UserRoleDTO> boToDTO(List<UserRoleBO> userRoleBOs);
	
	List<UserRoleBO> domainToBO(List<UserRole> userRoles);
}