package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleGroupParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.RoleGroupParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import com.zos.security.rbac.dto.response.detail.RoleGroupDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rolegroup")
public interface RoleGroupController {

	@PostMapping
	ResponseDTO<RoleGroupBaseDTO> create(@RequestBody RoleGroupBaseDTO roleGroupBaseDTO);

	@PatchMapping("/{id:\\w{32}}")
	ResponseDTO<RoleGroupBaseDTO> update(@PathVariable String id, @RequestBody RoleGroupBaseDTO roleGroupBaseDTO) throws Exception;

	@DeleteMapping("/{id:\\w{32}}")
	void delete(@PathVariable String id) throws Exception;

	@GetMapping("/{id:\\w{32}}")
	ResponseDTO<RoleGroupDetailDTO> getInfo(@PathVariable String id) throws Exception;

	@GetMapping
	ResponseDTO<Page<RoleGroupBaseDTO>> queryBase(RoleGroupParamBaseDTO roleGroupParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<RoleGroupDetailDTO>> queryDetail(RoleGroupParamDetailDTO roleGroupParamDetailDTO, Pageable pageable) throws Exception;

	@PutMapping("/parent/{parentId:\\w{32}}/{id:\\w{32}}")
	void changeParent(@PathVariable String parentId, @PathVariable String id) throws Exception;

	@GetMapping("/parent/{parentId:\\w{32}}")
	ResponseDTO<Page<RoleGroupBaseDTO>> queryByParentId(@PathVariable String parentId, Pageable pageable) throws Exception;

	@GetMapping("/children/{id:\\w{32}}")
	ResponseDTO<RoleGroupBaseDTO> queryParentById(@PathVariable String id) throws Exception;

	@DeleteMapping("/children/{parentId:\\w{32}}")
	void deleteByParentId(@PathVariable String parentId) throws Exception;

	@GetMapping("/user/{id:\\w{32}}")
	ResponseDTO<Page<UserBaseDTO>> queryUser(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/user/{id:\\w{32}}")
	ResponseDTO<Long> batchAddUser(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/user/{id:\\w{32}}")
	void batchRemoveUser(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/role/{id:\\w{32}}")
	ResponseDTO<Page<RoleBaseDTO>> queryRole(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/role/{id:\\w{32}}")
	ResponseDTO<Long> batchAddRole(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/role/{id:\\w{32}}")
	void batchRemoveRole(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

	@GetMapping("/team/{id:\\w{32}}")
	ResponseDTO<Page<TeamBaseDTO>> queryTeam(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/team/{id:\\w{32}}")
	ResponseDTO<Long> batchAddTeam(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/team/{id:\\w{32}}")
	void batchRemoveTeam(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;
}
