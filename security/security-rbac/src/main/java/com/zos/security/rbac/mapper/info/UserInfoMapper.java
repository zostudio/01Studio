package com.zos.security.rbac.mapper.info;

import com.zos.security.rbac.bo.resopnse.info.UserInfoBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.response.info.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper {

    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    // 详细信息
    UserInfoBO domainToBO(User user);

    List<UserInfoBO> domainToBO(List<User> users);

    User boToDomain(UserInfoBO userInfoBO);

    UserInfoDTO boToDTO(UserInfoBO userInfoBO);

    List<UserInfoDTO> boToDTO(List<UserInfoBO> userInfoBOs);

    UserInfoBO dtoToBO(UserInfoDTO userInfoDTO);

    List<UserInfoBO> dtoToBO(List<UserInfoDTO> userInfoDTOs);
}
