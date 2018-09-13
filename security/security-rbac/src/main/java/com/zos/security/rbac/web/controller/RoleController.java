package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.RoleParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
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
	ResponseDTO<Page<RoleBaseDTO>> querySimple(RoleParamBaseDTO roleParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<RoleDetailDTO>> queryDetail(RoleParamDetailDTO roleParamDetailDTO, Pageable pageable) throws Exception;
}
