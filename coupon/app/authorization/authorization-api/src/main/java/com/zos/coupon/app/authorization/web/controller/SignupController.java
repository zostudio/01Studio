package com.zos.coupon.app.authorization.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zos.security.rbac.dto.UserDTO;

@RestController
@RequestMapping("/authentication")
public interface SignupController {

	@PostMapping("/signup")
	public UserDTO signup(@RequestBody UserDTO user, HttpServletRequest request) throws Exception;
	
}
