package com.zos.security.rbac.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.UserConditionDTO;

@RestController
public interface UserController {

	@PostMapping
	public UserDTO create(@RequestBody UserDTO userDTO);
	
	@PutMapping("/{id:\\d+}")
	public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id);
	
	@GetMapping("/{id:\\d+}")
	public UserDTO getInfo(@PathVariable Long id);
	
	@GetMapping
	public Page<UserDTO> query(UserConditionDTO userConditionDTO, Pageable pageable);
}
