package com.zos.security.rbac.web.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zos.security.rbac.dto.UserConditionDTO;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.web.controller.UserController;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

	@Override
	@PostMapping
	@JsonView(UserDTO.SimpleView.class)
	public UserDTO create(@RequestBody UserDTO userDTO) {
		UserDTO result = new UserDTO(1L, "王", 18, "单王庄", "密码");
		return result;
	}

	@Override
	@PutMapping("/{id:\\d+}")
	@JsonView(UserDTO.SimpleView.class)
	public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		UserDTO result = new UserDTO(1L, "王", 18, "单王庄", "密码");
		return result;
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDTO.DetailView.class)
	public UserDTO getInfo(@PathVariable Long id) {
		UserDTO result = new UserDTO(1L, "王", 18, "单王庄", "密码");
		return result;
	}

	@Override
	@GetMapping
	@JsonView(UserDTO.SimpleView.class)
	public Page<UserDTO> query(UserConditionDTO userConditionDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = {"id"}) Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
