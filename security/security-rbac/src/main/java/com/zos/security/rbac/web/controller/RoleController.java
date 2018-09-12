package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.param.RoleParamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.zos.security.rbac.dto.RoleDTO;

import javax.validation.Valid;

@RequestMapping("/role")
public interface RoleController {

	@PostMapping
	public RoleDTO create(@Valid @RequestBody RoleDTO roleDTO, BindingResult errors);

	@PatchMapping("/{id:\\d+}")
	public RoleDTO update(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO, BindingResult errors);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id);
	
	@GetMapping("/{id:\\d+}")
	public RoleDTO getInfo(@PathVariable Long id);
	
	@GetMapping
	public Page<RoleDTO> query(RoleParamDTO roleConditionDTO, Pageable pageable);
}
