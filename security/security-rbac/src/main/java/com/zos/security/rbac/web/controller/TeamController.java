package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.TeamParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.TeamParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import com.zos.security.rbac.dto.response.detail.TeamDetailDTO;
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
	ResponseDTO<Page<TeamBaseDTO>> queryBase(TeamParamBaseDTO teamParamBaseDTO, Pageable pageable) throws Exception;

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

    @GetMapping("/user/{id:\\w{32}}")
    ResponseDTO<Page<UserBaseDTO>> queryUser(@PathVariable String id, Pageable pageable) throws Exception;

    @PatchMapping("/user/{id:\\w{32}}")
    ResponseDTO<Long> batchAddUser(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

    @DeleteMapping("/user/{id:\\w{32}}")
    void batchRemoveUser(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

    @GetMapping("/role/{id:\\w{32}}")
    ResponseDTO<Page<RoleBaseDTO>> queryRole(@PathVariable String id, Pageable pageable) throws Exception;

    @PatchMapping("/role/{id:\\w{32}}")
    ResponseDTO<Long> batchAddRole(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

    @DeleteMapping("/role/{id:\\w{32}}")
    void batchRemoveRole(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;

    @GetMapping("/rolegroup/{id:\\w{32}}")
    ResponseDTO<Page<RoleGroupBaseDTO>> queryRoleGroup(@PathVariable String id, Pageable pageable) throws Exception;

    @PatchMapping("/rolegroup/{id:\\w{32}}")
    ResponseDTO<Long> batchAddRoleGroup(@PathVariable String id, @RequestBody RelationsAddDTO relationsAddDTO) throws Exception;

    @DeleteMapping("/rolegroup/{id:\\w{32}}")
    void batchRemoveRoleGroup(@PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception;
}
