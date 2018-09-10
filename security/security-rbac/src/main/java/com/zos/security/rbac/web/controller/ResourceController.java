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

import com.zos.security.rbac.dto.ResourceDTO;
import com.zos.security.rbac.dto.condition.ResourceConditionDTO;

@RestController
@RequestMapping("/resource")
public interface ResourceController {

	@PostMapping
	public ResourceDTO create(@RequestBody ResourceDTO resourceDTO);
	
	@PutMapping("/{id:\\d+}")
	public ResourceDTO update(@PathVariable Long id, @RequestBody ResourceDTO resourceDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id);
	
	@GetMapping("/{id:\\d+}")
	public ResourceDTO getInfo(@PathVariable Long id);
	
	@GetMapping
	public Page<ResourceDTO> query(ResourceConditionDTO resourceConditionDTO, Pageable pageable);
}
