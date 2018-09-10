package com.zos.security.rbac.mapper;

import com.zos.security.rbac.bo.RoleGroupBO;
import com.zos.security.rbac.bo.RoleGroupConditionBO;
import com.zos.security.rbac.domain.RoleGroup;
import com.zos.security.rbac.dto.RoleGroupDTO;
import com.zos.security.rbac.dto.condition.RoleGroupConditionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
