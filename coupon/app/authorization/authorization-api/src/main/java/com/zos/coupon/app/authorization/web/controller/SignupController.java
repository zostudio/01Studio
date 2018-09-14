package com.zos.coupon.app.authorization.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.response.info.UserInfoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authentication")
public interface SignupController {

	@PostMapping("/signup")
	public ResponseDTO<UserInfoDTO> signup(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) throws Exception;
	
}
