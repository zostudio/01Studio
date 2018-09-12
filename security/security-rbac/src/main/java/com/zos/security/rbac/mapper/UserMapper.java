package com.zos.security.rbac.mapper;

import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserConditionBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.param.UserParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User boToDomain(UserBO userBO);
	
	UserBO domainToBO(User user);
	
	List<UserBO> domainToBO(List<User> users);
	
	UserBO dtoToBO(UserDTO userDTO);
	
	UserDTO boToDTO(UserBO userBO);
	
	List<UserDTO> boToDTO(List<UserBO> userBO);
	
	UserConditionBO dtoToBO(UserParamDTO userConditionDTO);
	
}
