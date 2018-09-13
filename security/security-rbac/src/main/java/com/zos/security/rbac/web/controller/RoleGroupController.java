package com.zos.security.rbac.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zos.security.rbac.dto.param.RoleGroupParamDTO;
import com.zos.security.rbac.dto.RoleGroupDTO;

@RestController
@RequestMapping("/rolegroup")
public interface RoleGroupController {

	@PostMapping
	public RoleGroupDTO create(@RequestBody RoleGroupDTO roleGroupDTO);
	
	@PutMapping("/{id:\\d+}")
	public RoleGroupDTO update(@PathVariable String id, @RequestBody RoleGroupDTO roleGroupDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id);
	
	@GetMapping("/{id:\\d+}")
	public RoleGroupDTO getInfo(@PathVariable String id);
	
	@GetMapping
	public Page<RoleGroupDTO> query(RoleGroupParamDTO roleGroupConditionDTO, Pageable pageable);
}
