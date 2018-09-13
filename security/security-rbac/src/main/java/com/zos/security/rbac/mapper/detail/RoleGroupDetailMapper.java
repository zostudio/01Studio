package com.zos.security.rbac.mapper.detail;

import com.zos.security.rbac.bo.resopnse.detail.RoleGroupDetailBO;
import com.zos.security.rbac.domain.RoleGroup;
import com.zos.security.rbac.dto.response.detail.RoleGroupDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleGroupDetailMapper {

    RoleGroupDetailMapper INSTANCE = Mappers.getMapper(RoleGroupDetailMapper.class);

    // 详细信息
    RoleGroupDetailBO domainToBO(RoleGroup roleGroup);

    List<RoleGroupDetailBO> domainToBO(List<RoleGroup> roleGroups);

    RoleGroupDetailDTO boToDTO(RoleGroupDetailBO roleGroupDetailBO);

    List<RoleGroupDetailDTO> boToDTO(List<RoleGroupDetailBO> roleGroupDetailBOs);
}
