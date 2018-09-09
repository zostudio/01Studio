package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.dto.RoleConditionDTO;
import com.zos.security.rbac.dto.RoleDTO;
import com.zos.security.rbac.mapper.RoleMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.web.controller.RoleController;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Data
@Transactional
@RestController
public class RoleControllerImpl implements RoleController {
	
	@Autowired
	private RoleService roleService;

	@Override
	public RoleDTO create(@Valid @RequestBody RoleDTO roleDTO, BindingResult errors) {
		return RoleMapper.INSTANCE.boToDTO(roleService.create(RoleMapper.INSTANCE.dtoToBO(roleDTO)));
	}

	@Override
	public RoleDTO update(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO, BindingResult errors) {
		return RoleMapper.INSTANCE.boToDTO(roleService.update(id, RoleMapper.INSTANCE.dtoToBO(roleDTO)));
	}

	@Override
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

	@Override
	public RoleDTO getInfo(@PathVariable Long id) {
		return RoleMapper.INSTANCE.boToDTO(roleService.getInfo(id));
	}

	@Override
	public Page<RoleDTO> query(RoleConditionDTO roleConditionDTO, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 10, sort = {"id"})  Pageable pageable) {
		Page<RoleBO> pageRoleBO = roleService.query(RoleMapper.INSTANCE.dtoToBO(roleConditionDTO), pageable);
		Page<RoleDTO> pageRoleDTO = QueryResultConverter.convert(RoleMapper.INSTANCE.boToDTO(pageRoleBO.getContent()), pageable, pageRoleBO.getTotalElements());
		return pageRoleDTO;
	}
	
	

}
