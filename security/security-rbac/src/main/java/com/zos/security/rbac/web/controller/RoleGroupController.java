package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleGroupParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.RoleGroupParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
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
	ResponseDTO<Page<RoleGroupBaseDTO>> querySimple(RoleGroupParamBaseDTO roleGroupParamBaseDTO, Pageable pageable) throws Exception;

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
}
