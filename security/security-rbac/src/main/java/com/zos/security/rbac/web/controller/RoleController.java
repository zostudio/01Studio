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

import com.zos.security.rbac.dto.RoleDTO;
import com.zos.security.rbac.dto.RoleConditionDTO;

@RestController
@RequestMapping("/role")
public interface RoleController {

	@PostMapping
	public RoleDTO create(@RequestBody RoleDTO roleDTO);
	
	@PutMapping("/{id:\\d+}")
	public RoleDTO update(@PathVariable Long id, @RequestBody RoleDTO roleDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id);
	
	@GetMapping("/{id:\\d+}")
	public RoleDTO getInfo(@PathVariable Long id);
	
	@GetMapping
	public Page<RoleDTO> query(RoleConditionDTO roleConditionDTO, Pageable pageable);
}
