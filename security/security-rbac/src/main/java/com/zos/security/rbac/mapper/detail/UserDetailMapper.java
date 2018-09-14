package com.zos.security.rbac.mapper.detail;

import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.response.detail.UserDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDetailMapper {

    UserDetailMapper INSTANCE = Mappers.getMapper(UserDetailMapper.class);

    // 详细信息
    UserDetailBO domainToBO(User user);

    List<UserDetailBO> domainToBO(List<User> users);

    User boToDomain(UserDetailBO userDetailBO);

    UserDetailDTO boToDTO(UserDetailBO userDetailBO);

    List<UserDetailDTO> boToDTO(List<UserDetailBO> userDetailBOs);
}
