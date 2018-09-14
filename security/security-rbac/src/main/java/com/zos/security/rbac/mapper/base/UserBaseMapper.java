package com.zos.security.rbac.mapper.base;

import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserBaseMapper {

    UserBaseMapper INSTANCE = Mappers.getMapper(UserBaseMapper.class);

    // 基本信息
    User boToDomain(UserBaseBO userBaseBO);

    UserBaseBO domainToBO(User user);

    List<UserBaseBO> domainToBO(List<User> users);

    UserBaseBO dtoToBO(UserBaseDTO userBaseDTO);

    UserBaseDTO boToDTO(UserBaseBO userBaseBO);

    List<UserBaseDTO> boToDTO(List<UserBaseBO> userBaseBOs);
}
