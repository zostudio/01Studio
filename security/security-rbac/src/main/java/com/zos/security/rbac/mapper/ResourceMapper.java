package com.zos.security.rbac.mapper;

import com.zos.security.rbac.bo.ResourceBO;
import com.zos.security.rbac.bo.ResourceConditionBO;
import com.zos.security.rbac.domain.Resource;
import com.zos.security.rbac.dto.ResourceDTO;
import com.zos.security.rbac.dto.param.ResourceParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {
	
	ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);
	
	Resource boToDomain(ResourceBO resourceBO);
	
	ResourceBO domainToBO(Resource resource);
	
	List<ResourceBO> domainToBO(List<Resource> resources);
	
	ResourceBO dtoToBO(ResourceDTO resourceDTO);
	
	ResourceDTO boToDTO(ResourceBO resourceBO);
	
	List<ResourceDTO> boToDTO(List<ResourceBO> resourceBO);
	
	ResourceConditionBO dtoToBo(ResourceParamDTO resourceConditionDTO);
	
}
