package com.zos.security.rbac.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zos.security.rbac.bo.RoleGroupBO;
import com.zos.security.rbac.bo.RoleGroupConditionBO;
import com.zos.security.rbac.domain.RoleGroup;
import com.zos.security.rbac.dto.RoleGroupConditionDTO;
import com.zos.security.rbac.dto.RoleGroupDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleGroupMapper {
	
	RoleGroupMapper INSTANCE = Mappers.getMapper(RoleGroupMapper.class);
	
	RoleGroup boToDomain(RoleGroupBO roleGroupBO);
	
	RoleGroupBO domainToBO(RoleGroup roleGroup);
	
	List<RoleGroupBO> domainToBO(List<RoleGroup> roleGroups);
	
	RoleGroupBO dtoToBO(RoleGroupDTO roleGroupDTO);
	
	RoleGroupDTO boToDTO(RoleGroupBO roleGroupBO);
	
	List<RoleGroupDTO> boToDTO(List<RoleGroupBO> roleGroupBO);
	
	RoleGroupConditionBO dtoToBO(RoleGroupConditionDTO roleGroupConditionDTO);
	
}
