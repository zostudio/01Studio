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
import com.zos.security.rbac.bo.ResourceBO;
import com.zos.security.rbac.dto.condition.ResourceConditionDTO;
import com.zos.security.rbac.dto.ResourceDTO;
import com.zos.security.rbac.mapper.ResourceMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.ResourceService;
import com.zos.security.rbac.web.controller.ResourceController;

@RestController
@RequestMapping("/resource")
public class ResourceControllerImpl implements ResourceController {
	
	@Autowired
	ResourceService resourceService;

	@Override
	@PostMapping
	@JsonView(ResourceDTO.SimpleView.class)
	public ResourceDTO create(@RequestBody ResourceDTO resourceDTO) {
		return ResourceMapper.INSTANCE.boToDTO(resourceService.create(ResourceMapper.INSTANCE.dtoToBO(resourceDTO)));
	}

	@Override
	@PutMapping("/{id:\\d+}")
	@JsonView(ResourceDTO.SimpleView.class)
	public ResourceDTO update(@PathVariable Long id, @RequestBody ResourceDTO resourceDTO) {
		return ResourceMapper.INSTANCE.boToDTO(resourceService.update(ResourceMapper.INSTANCE.dtoToBO(resourceDTO)));
	}

	@Override
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) {
		resourceService.delete(id);
	}

	@Override
	@GetMapping("/{id:\\d+}")
	@JsonView(ResourceDTO.DetailView.class)
	public ResourceDTO getInfo(@PathVariable Long id) {
		return ResourceMapper.INSTANCE.boToDTO(resourceService.getInfo(id));
	}

	@Override
	@GetMapping
	@JsonView(ResourceDTO.SimpleView.class)
	public Page<ResourceDTO> query(ResourceConditionDTO resourceConditionDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = {"id"}) Pageable pageable) {
		Page<ResourceBO> pageResourceBO = resourceService.query(ResourceMapper.INSTANCE.dtoToBo(resourceConditionDTO), pageable);
		Page<ResourceDTO> pageResourceDTO = QueryResultConverter.convert(ResourceMapper.INSTANCE.boToDTO(pageResourceBO.getContent()), pageable, pageResourceBO.getTotalElements());
		return pageResourceDTO;
	}

}
