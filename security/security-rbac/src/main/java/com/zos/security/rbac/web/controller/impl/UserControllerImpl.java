package com.zos.security.rbac.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.dto.UserConditionDTO;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.UserRoleDTO;
import com.zos.security.rbac.dto.UserRoleRelationDTO;
import com.zos.security.rbac.mapper.UserMapper;
import com.zos.security.rbac.mapper.UserRoleMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.web.controller.UserController;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
	
	@Autowired
	UserService userService;

	@Override
	@PostMapping
	@JsonView(UserDTO.SimpleView.class)
	public UserDTO create(@RequestBody UserDTO userDTO) {
		return UserMapper.INSTANCE.boToDTO(userService.create(UserMapper.INSTANCE.dtoToBO(userDTO)));
	}

	@Override
	@PutMapping("/{id:\\d+}")
	@JsonView(UserDTO.SimpleView.class)
	public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return UserMapper.INSTANCE.boToDTO(userService.update(UserMapper.INSTANCE.dtoToBO(userDTO)));
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@Override
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDTO.DetailView.class)
	public UserDTO getInfo(@PathVariable Long id) {
		return UserMapper.INSTANCE.boToDTO(userService.getInfo(id));
	}

	@Override
	@GetMapping
	public Page<UserDTO> query(UserConditionDTO userConditionDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = {"id"}) Pageable pageable) {
		Page<UserBO> pageUserBO = userService.query(UserMapper.INSTANCE.dtoToBO(userConditionDTO), pageable);
		Page<UserDTO> pageUserDTO = QueryResultConverter.convert(UserMapper.INSTANCE.boToDTO(pageUserBO.getContent()), pageable, pageUserBO.getTotalElements());
		return pageUserDTO;
	}

	@Override
	@PostMapping("/roles")
	public List<UserRoleDTO> addRoles(@RequestBody UserRoleRelationDTO userRoleRelationDTO) {
		return UserRoleMapper.INSTANCE.boToDTO(userService.addRoles(UserRoleMapper.INSTANCE.dtoToBO(userRoleRelationDTO)));
	}

	@Override
	@DeleteMapping("/roles/{id:\\d+}")
	public void delRoles(@PathVariable Long id, @RequestBody UserRoleRelationDTO userRoleRelationDTO) {
		userService.delRoles(id, UserRoleMapper.INSTANCE.dtoToBO(userRoleRelationDTO));
	}
	
}
