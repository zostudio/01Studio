package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.RoleParamDetailDTO;
import com.zos.security.rbac.dto.response.base.*;
import com.zos.security.rbac.dto.response.detail.RoleDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/role")
public interface RoleController {

	@PostMapping
	ResponseDTO<RoleBaseDTO> create(@RequestBody RoleBaseDTO roleBaseDTO);

	@PatchMapping("/{id:\\w{32}}")
	ResponseDTO<RoleBaseDTO> update(@PathVariable String id, @RequestBody RoleBaseDTO roleBaseDTO) throws Exception;

	@DeleteMapping("/{id:\\w{32}}")
	void delete(@PathVariable String id) throws Exception;

	@GetMapping("/{id:\\w{32}}")
	ResponseDTO<RoleDetailDTO> getInfo(@PathVariable String id) throws Exception;

	@GetMapping
	ResponseDTO<Page<RoleBaseDTO>> queryBase(RoleParamBaseDTO roleParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<RoleDetailDTO>> queryDetail(RoleParamDetailDTO roleParamDetailDTO, Pageable pageable) throws Exception;

	@GetMapping("/user/{id:\\w{32}}")
	ResponseDTO<Page<UserBaseDTO>> queryUser(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/user/{id:\\w{32}}")
	ResponseDTO<Long> batchAddUser(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/user/{id:\\w{32}}")
	void batchRemoveUser(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/rolegroup/{id:\\w{32}}")
	ResponseDTO<Page<RoleGroupBaseDTO>> queryRoleGroup(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/rolegroup/{id:\\w{32}}")
	ResponseDTO<Long> batchAddRoleGroup(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/rolegroup/{id:\\w{32}}")
	void batchRemoveRoleGroup(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/resource/{id:\\w{32}}")
	ResponseDTO<Page<ResourceBaseDTO>> queryResource(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/resource/{id:\\w{32}}")
	ResponseDTO<Long> batchAddResource(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/resource/{id:\\w{32}}")
	void batchRemoveResource(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/team/{id:\\w{32}}")
	ResponseDTO<Page<TeamBaseDTO>> queryTeam(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/team/{id:\\w{32}}")
	ResponseDTO<Long> batchAddTeam(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/team/{id:\\w{32}}")
	void batchRemoveTeam(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;
}
