package com.zos.security.rbac.mapper.detail;

import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.dto.response.detail.RoleDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDetailMapper {

    RoleDetailMapper INSTANCE = Mappers.getMapper(RoleDetailMapper.class);

    // 详细信息
    RoleDetailBO domainToBO(Role role);

    List<RoleDetailBO> domainToBO(List<Role> roles);

    RoleDetailDTO boToDTO(RoleDetailBO roleDetailBO);

    List<RoleDetailDTO> boToDTO(List<RoleDetailBO> roleDetailBOs);
}
