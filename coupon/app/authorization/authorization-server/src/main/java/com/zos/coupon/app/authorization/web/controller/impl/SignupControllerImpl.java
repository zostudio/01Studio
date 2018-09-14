package com.zos.coupon.app.authorization.web.controller.impl;

import com.zos.coupon.app.authorization.web.controller.SignupController;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.response.info.UserInfoDTO;
import com.zos.security.rbac.mapper.info.UserInfoMapper;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.support.constance.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authentication")
public class SignupControllerImpl implements SignupController {

	@Autowired
	UserService userService;

	@Override
	@PostMapping("/signup")
	public ResponseDTO<UserInfoDTO> signup(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) throws Exception {
		ResponseDTO<UserInfoDTO> responseDTO = new ResponseDTO<UserInfoDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(UserInfoMapper.INSTANCE.boToDTO(userService.create(UserInfoMapper.INSTANCE.dtoToBO(userInfoDTO))));
		return responseDTO;
	}

}
