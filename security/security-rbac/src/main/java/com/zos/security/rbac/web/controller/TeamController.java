package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.detail.TeamParamDetailDTO;
import com.zos.security.rbac.dto.param.base.TeamParamBaseDTO;
import com.zos.security.rbac.dto.response.detail.TeamDetailDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/team")
public interface TeamController {

	@PostMapping
	ResponseDTO<TeamBaseDTO> create(@RequestBody TeamBaseDTO teamBaseDTO);
	
	@PatchMapping("/{id:\\w{32}}")
	ResponseDTO<TeamBaseDTO> update(@PathVariable String id, @RequestBody TeamBaseDTO teamBaseDTO) throws Exception;
	
	@DeleteMapping("/{id:\\w{32}}")
	void delete(@PathVariable String id) throws Exception;
	
	@GetMapping("/{id:\\w{32}}")
	ResponseDTO<TeamDetailDTO> getInfo(@PathVariable String id) throws Exception;
	
	@GetMapping
	ResponseDTO<Page<TeamBaseDTO>> querySimple(TeamParamBaseDTO teamParamBaseDTO, Pageable pageable) throws Exception;

	@GetMapping("/detail")
	ResponseDTO<Page<TeamDetailDTO>> queryDetail(TeamParamDetailDTO teamParamDetailDTO, Pageable pageable) throws Exception;

	@PutMapping("/parent/{parentId:\\w{32}}/{id:\\w{32}}")
    void changeParent(@PathVariable String parentId, @PathVariable String id) throws Exception;

    @GetMapping("/parent/{parentId:\\w{32}}")
    ResponseDTO<Page<TeamBaseDTO>> queryByParentId(@PathVariable String parentId, Pageable pageable) throws Exception;

    @GetMapping("/children/{id:\\w{32}}")
    ResponseDTO<TeamBaseDTO> queryParentById(@PathVariable String id) throws Exception;

    @DeleteMapping("/children/{parentId:\\w{32}}")
    void deleteByParentId(@PathVariable String parentId) throws Exception;
}
