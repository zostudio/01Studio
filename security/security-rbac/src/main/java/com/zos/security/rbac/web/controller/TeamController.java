package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.detail.TeamParamDetailDTO;
import com.zos.security.rbac.dto.param.simple.TeamParamSimpleDTO;
import com.zos.security.rbac.dto.response.detail.TeamDetailDTO;
import com.zos.security.rbac.dto.response.simple.TeamSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/team")
public interface TeamController {

	@PostMapping
	public ResponseDTO<TeamSimpleDTO> create(@RequestBody TeamSimpleDTO teamSimpleDTO);
	
	@PatchMapping("/{id:\\d+}")
	public ResponseDTO<TeamSimpleDTO> update(@PathVariable Long id, @RequestBody TeamSimpleDTO teamSimpleDTO) throws Exception;
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id) throws Exception;
	
	@GetMapping("/{id:\\d+}")
	public ResponseDTO<TeamDetailDTO> getInfo(@PathVariable Long id) throws Exception;
	
	@GetMapping
	public ResponseDTO<Page<TeamSimpleDTO>> querySimple(TeamParamSimpleDTO teamParamSimpleDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	public ResponseDTO<Page<TeamDetailDTO>> queryDetail(TeamParamDetailDTO teamParamDetailDTO, Pageable pageable) throws Exception;

	@PutMapping("/parent/{parentId:\\d+}/{id:\\d+}")
    public void changeParent(@PathVariable Long parentId, @PathVariable Long id) throws Exception;

    @GetMapping("/parent/{parentId:\\d+}")
    public ResponseDTO<Page<TeamSimpleDTO>> queryByParentId(@PathVariable Long parentId, Pageable pageable) throws Exception;

    @GetMapping("/children/{id:\\d+}")
    public ResponseDTO<TeamSimpleDTO> queryParentById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/children/{parentId:\\d+}")
    public void deleteByParentId(@PathVariable Long parentId) throws Exception;


}
