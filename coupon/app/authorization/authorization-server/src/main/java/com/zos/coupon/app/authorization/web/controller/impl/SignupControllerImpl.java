package com.zos.coupon.app.authorization.web.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zos.coupon.app.authorization.web.controller.SignupController;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.mapper.UserMapper;
import com.zos.security.rbac.service.UserService;

@RestController
@RequestMapping("/authentication")
public class SignupControllerImpl implements SignupController {

	@Autowired
	UserService userService;

	@Override
	@PostMapping("/signup")
	@JsonView(UserDTO.SimpleView.class)
	public UserDTO signup(@RequestBody UserDTO user, HttpServletRequest request) throws Exception {
		return UserMapper.INSTANCE.boToDTO(userService.create(UserMapper.INSTANCE.dtoToBO(user)));
	}

}
