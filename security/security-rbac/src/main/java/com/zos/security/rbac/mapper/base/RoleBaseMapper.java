package com.zos.security.rbac.mapper.base;

import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleBaseMapper {

    RoleBaseMapper INSTANCE = Mappers.getMapper(RoleBaseMapper.class);

    // 基本信息
    Role boToDomain(RoleBaseBO roleBaseBO);

    RoleBaseBO domainToBO(Role role);

    List<RoleBaseBO> domainToBO(List<Role> roles);

    RoleBaseBO dtoToBO(RoleBaseDTO roleBaseDTO);

    RoleBaseDTO boToDTO(RoleBaseBO roleBaseBO);

    List<RoleBaseDTO> boToDTO(List<RoleBaseBO> roleBaseBOs);
}
