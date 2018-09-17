package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.ResourceParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.ResourceParamDetailDTO;
import com.zos.security.rbac.dto.response.base.ResourceBaseDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.detail.ResourceDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/resource")
public interface ResourceController {

	@PostMapping
	ResponseDTO<ResourceBaseDTO> create(@RequestBody ResourceBaseDTO resourceBaseDTO);

	@PatchMapping("/{id:\\w{32}}")
	ResponseDTO<ResourceBaseDTO> update(@PathVariable String id, @RequestBody ResourceBaseDTO resourceBaseDTO) throws Exception;

	@DeleteMapping("/{id:\\w{32}}")
	void delete(@PathVariable String id) throws Exception;

	@GetMapping("/{id:\\w{32}}")
	ResponseDTO<ResourceDetailDTO> getInfo(@PathVariable String id) throws Exception;

	@GetMapping
	ResponseDTO<Page<ResourceBaseDTO>> queryBase(ResourceParamBaseDTO resourceParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<ResourceDetailDTO>> queryDetail(ResourceParamDetailDTO resourceParamDetailDTO, Pageable pageable) throws Exception;

	@PutMapping("/parent/{parentId:\\w{32}}/{id:\\w{32}}")
	void changeParent(@PathVariable String parentId, @PathVariable String id) throws Exception;

	@GetMapping("/parent/{parentId:\\w{32}}")
	ResponseDTO<Page<ResourceBaseDTO>> queryByParentId(@PathVariable String parentId, Pageable pageable) throws Exception;

	@GetMapping("/children/{id:\\w{32}}")
	ResponseDTO<ResourceBaseDTO> queryParentById(@PathVariable String id) throws Exception;

	@DeleteMapping("/children/{parentId:\\w{32}}")
	void deleteByParentId(@PathVariable String parentId) throws Exception;

	@GetMapping("/role/{id:\\w{32}}")
	ResponseDTO<Page<RoleBaseDTO>> queryRole(@PathVariable String id, Pageable pageable) throws Exception;

	@PatchMapping("/role/{id:\\w{32}}")
	ResponseDTO<Long> batchAddRole(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

	@DeleteMapping("/role/{id:\\w{32}}")
	void batchRemoveRole(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;
}
