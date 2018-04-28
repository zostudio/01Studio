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
import com.zos.security.rbac.bo.DepartmentBO;
import com.zos.security.rbac.dto.DepartmentConditionDTO;
import com.zos.security.rbac.dto.DepartmentDTO;
import com.zos.security.rbac.mapper.DepartmentMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.DepartmentService;
import com.zos.security.rbac.web.controller.DepartmentController;

@RestController
@RequestMapping("/department")
public class DepartmentControllerImpl implements DepartmentController {
	
	@Autowired
	DepartmentService departmentService;

	@Override
	@PostMapping
	@JsonView(DepartmentDTO.SimpleView.class)
	public DepartmentDTO create(@RequestBody DepartmentDTO departmentDTO) {
		return DepartmentMapper.INSTANCE.boToDTO(departmentService.create(DepartmentMapper.INSTANCE.dtoToBO(departmentDTO)));
	}

	@Override
	@PutMapping("/{id:\\d+}")
	@JsonView(DepartmentDTO.SimpleView.class)
	public DepartmentDTO update(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
		return DepartmentMapper.INSTANCE.boToDTO(departmentService.update(DepartmentMapper.INSTANCE.dtoToBO(departmentDTO)));
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) {
		departmentService.delete(id);
	}

	@Override
	@GetMapping("/{id:\\d+}")
	@JsonView(DepartmentDTO.DetailView.class)
	public DepartmentDTO getInfo(@PathVariable Long id) {
		return DepartmentMapper.INSTANCE.boToDTO(departmentService.getInfo(id));
	}

	@Override
	@GetMapping
	@JsonView(DepartmentDTO.SimpleView.class)
	public Page<DepartmentDTO> query(DepartmentConditionDTO departmentConditionDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = {"id"}) Pageable pageable) {
		Page<DepartmentBO> pageDepartmentBO = departmentService.query(DepartmentMapper.INSTANCE.dtoToBO(departmentConditionDTO), pageable);
		Page<DepartmentDTO> pageDepartmentDTO = QueryResultConverter.convert(DepartmentMapper.INSTANCE.boToDTO(pageDepartmentBO.getContent()), pageable, pageDepartmentBO.getTotalElements());
		return pageDepartmentDTO;
	}

}
