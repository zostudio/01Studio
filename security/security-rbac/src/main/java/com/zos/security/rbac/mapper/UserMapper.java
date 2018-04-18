package com.zos.security.rbac.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.domain.User;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User boToDomain(UserBO userBO);
	
	UserBO domainToBo(User user);
	
}
