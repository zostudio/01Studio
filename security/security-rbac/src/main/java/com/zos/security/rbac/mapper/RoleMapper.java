package com.zos.security.rbac.mapper;

import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.bo.RoleConditionBO;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.dto.RoleDTO;
import com.zos.security.rbac.dto.param.RoleParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
	
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
	
	Role boToDomain(RoleBO roleBO);
	
	RoleBO domainToBO(Role role);
	
	List<RoleBO> domainToBO(List<Role> roles);
	
	RoleBO dtoToBO(RoleDTO roleDTO);
	
	RoleDTO boToDTO(RoleBO roleBO);
	
	List<RoleDTO> boToDTO(List<RoleBO> roleBO);
	
	RoleConditionBO dtoToBO(RoleParamDTO roleConditionDTO);
	
}
