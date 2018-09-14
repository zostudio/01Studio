package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.UserParamDetailDTO;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import com.zos.security.rbac.dto.response.detail.UserDetailDTO;
import com.zos.security.rbac.dto.response.info.UserInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserController {

	@PostMapping
	ResponseDTO<UserInfoDTO> create(@RequestBody UserInfoDTO userInfoDTO);

	@PatchMapping("/{id:\\w{32}}")
	ResponseDTO<UserInfoDTO> update(@PathVariable String id, @RequestBody UserInfoDTO userInfoDTO) throws Exception;

	@DeleteMapping("/{id:\\w{32}}")
	void delete(@PathVariable String id) throws Exception;

	@GetMapping("/{id:\\w{32}}")
	ResponseDTO<UserDetailDTO> getInfo(@PathVariable String id) throws Exception;

	@GetMapping
	ResponseDTO<Page<UserBaseDTO>> querySimple(UserParamBaseDTO userParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<UserDetailDTO>> queryDetail(UserParamDetailDTO userParamDetailDTO, Pageable pageable) throws Exception;

	@GetMapping("/authentication")
    ResponseDTO<Authentication> getAuthentication(Authentication authentication);
	
	@GetMapping("/exist/username/{username:\\w+}")
    ResponseDTO<Boolean> existByUsername(@PathVariable String username);

	@GetMapping("/exist/email/{email:\\w+}")
    ResponseDTO<Boolean> existByEmail(@PathVariable String email);

	@GetMapping("/exist/phone/{phone:\\w+}")
    ResponseDTO<Boolean> existByPhone(@PathVariable String phone);

	//TODO 添加角色
//	@PostMapping("/roles")
//	public List<UserRoleDTO> addRoles(@RequestBody UserRoleRelationDTO userRoleRelationDTO);
//
//	@GetMapping("/roles/{id:\\d+}")
//	public Set<User.RoleCache> getRoles(@PathVariable String id, Authentication authentication);
//
//	@DeleteMapping("/roles/{id:\\d+}")
//	public void delRoles(@PathVariable String id, @RequestBody UserRoleRelationDTO userRoleRelationDTO);
//
}
