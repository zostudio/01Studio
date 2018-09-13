package com.zos.security.rbac.mapper.detail;

import com.zos.security.rbac.bo.resopnse.detail.ResourceDetailBO;
import com.zos.security.rbac.domain.Resource;
import com.zos.security.rbac.dto.response.detail.ResourceDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceDetailMapper {

    ResourceDetailMapper INSTANCE = Mappers.getMapper(ResourceDetailMapper.class);

    // 详细信息
    ResourceDetailBO domainToBO(Resource resource);

    List<ResourceDetailBO> domainToBO(List<Resource> resources);

    ResourceDetailDTO boToDTO(ResourceDetailBO resourceDetailBO);

    List<ResourceDetailDTO> boToDTO(List<ResourceDetailBO> resourceDetailBOs);
}
