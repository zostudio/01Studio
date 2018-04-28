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

import com.zos.security.rbac.dto.DepartmentDTO;
import com.zos.security.rbac.dto.DepartmentConditionDTO;

@RestController
@RequestMapping("/department")
public interface DepartmentController {

	@PostMapping
	public DepartmentDTO create(@RequestBody DepartmentDTO departmentDTO);
	
	@PutMapping("/{id:\\d+}")
	public DepartmentDTO update(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id);
	
	@GetMapping("/{id:\\d+}")
	public DepartmentDTO getInfo(@PathVariable Long id);
	
	@GetMapping
	public Page<DepartmentDTO> query(DepartmentConditionDTO departmentConditionDTO, Pageable pageable);
}
