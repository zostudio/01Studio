package com.zos.security.rbac.mapper.base;

import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.domain.RoleGroup;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleGroupBaseMapper {

    RoleGroupBaseMapper INSTANCE = Mappers.getMapper(RoleGroupBaseMapper.class);

    // 基本信息
    RoleGroup boToDomain(RoleGroupBaseBO roleGroupBaseBO);

    RoleGroupBaseBO domainToBO(RoleGroup roleGroup);

    List<RoleGroupBaseBO> domainToBO(List<RoleGroup> roleGroups);

    RoleGroupBaseBO dtoToBO(RoleGroupBaseDTO roleGroupBaseDTO);

    RoleGroupBaseDTO boToDTO(RoleGroupBaseBO roleGroupBaseBO);

    List<RoleGroupBaseDTO> boToDTO(List<RoleGroupBaseBO> roleGroupBaseBOs);
}
