package com.zos.coupon.app.resource.mapper;

import org.mapstruct.Mapper;

import com.zos.coupon.app.resource.domain.User;
import com.zos.coupon.app.resource.dto.UserDto;

@Mapper
public interface UserMapper {
	UserDto domainToDto(User user);
}
