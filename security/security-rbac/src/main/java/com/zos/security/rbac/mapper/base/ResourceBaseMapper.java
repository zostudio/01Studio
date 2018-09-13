package com.zos.security.rbac.mapper.base;

import com.zos.security.rbac.bo.resopnse.base.ResourceBaseBO;
import com.zos.security.rbac.domain.Resource;
import com.zos.security.rbac.dto.response.base.ResourceBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceBaseMapper {

    ResourceBaseMapper INSTANCE = Mappers.getMapper(ResourceBaseMapper.class);

    // 基本信息
    Resource boToDomain(ResourceBaseBO resourceBaseBO);

    ResourceBaseBO domainToBO(Resource resource);

    List<ResourceBaseBO> domainToBO(List<Resource> resources);

    ResourceBaseBO dtoToBO(ResourceBaseDTO resourceBaseDTO);

    ResourceBaseDTO boToDTO(ResourceBaseBO resourceBaseBO);

    List<ResourceBaseDTO> boToDTO(List<ResourceBaseBO> resourceBaseBOs);
}
