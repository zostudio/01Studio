package com.zos.security.rbac.web.controller.impl;

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
import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.dto.RoleConditionDTO;
import com.zos.security.rbac.dto.RoleDTO;
import com.zos.security.rbac.mapper.RoleMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.web.controller.RoleController;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {
	
	@Autowired
	RoleService roleService;

	@Override
	@PostMapping
	@JsonView(RoleDTO.SimpleView.class)
	public RoleDTO create(@RequestBody RoleDTO roleDTO) {
		return RoleMapper.INSTANCE.boToDTO(roleService.create(RoleMapper.INSTANCE.dtoToBO(roleDTO)));
	}

	@Override
	@PutMapping("/{id:\\d+}")
	@JsonView(RoleDTO.SimpleView.class)
	public RoleDTO update(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
		return RoleMapper.INSTANCE.boToDTO(roleService.update(RoleMapper.INSTANCE.dtoToBO(roleDTO)));
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

	@Override
	@GetMapping("/{id:\\d+}")
	@JsonView(RoleDTO.DetailView.class)
	public RoleDTO getInfo(@PathVariable Long id) {
		return RoleMapper.INSTANCE.boToDTO(roleService.getInfo(id));
	}

	@Override
	@GetMapping
	@JsonView(RoleDTO.SimpleView.class)
	public Page<RoleDTO> query(RoleConditionDTO roleConditionDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = {"id"}) Pageable pageable) {
		Page<RoleBO> pageRoleBO = roleService.query(RoleMapper.INSTANCE.dtoToBO(roleConditionDTO), pageable);
		Page<RoleDTO> pageRoleDTO = QueryResultConverter.convert(RoleMapper.INSTANCE.boToDTO(pageRoleBO.getContent()), pageable, pageRoleBO.getTotalElements());
		return pageRoleDTO;
	}
	
	

}
