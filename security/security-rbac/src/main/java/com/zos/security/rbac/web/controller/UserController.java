package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.UserParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
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
	ResponseDTO<Page<UserBaseDTO>> queryBase(UserParamBaseDTO userParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<UserDetailDTO>> queryDetail(UserParamDetailDTO userParamDetailDTO, Pageable pageable) throws Exception;

	@GetMapping("/authentication")
    ResponseDTO<UserInfoDTO> getAuthentication(Authentication authentication);
	
	@GetMapping("/exist/username/{username:\\w+}")
    ResponseDTO<Boolean> existByUsername(@PathVariable String username);

	@GetMapping("/exist/email/{email:\\w+}")
    ResponseDTO<Boolean> existByEmail(@PathVariable String email);

	@GetMapping("/exist/phone/{phone:\\w+}")
    ResponseDTO<Boolean> existByPhone(@PathVariable String phone);

	@GetMapping("/role/{id:\\w{32}}")
	ResponseDTO<Page<RoleBaseDTO>> queryRole(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/role/{id:\\w{32}}")
	ResponseDTO<Long> batchAddRole(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/role/{id:\\w{32}}")
	void batchRemoveRole(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/rolegroup/{id:\\w{32}}")
	ResponseDTO<Page<RoleGroupBaseDTO>> queryRoleGroup(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/rolegroup/{id:\\w{32}}")
	ResponseDTO<Long> batchAddRoleGroup(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/rolegroup/{id:\\w{32}}")
	void batchRemoveRoleGroup(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/team/{id:\\w{32}}")
	ResponseDTO<Page<TeamBaseDTO>> queryTeam(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/team/{id:\\w{32}}")
	ResponseDTO<Long> batchAddTeam(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/team/{id:\\w{32}}")
	void batchRemoveTeam(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;
}
