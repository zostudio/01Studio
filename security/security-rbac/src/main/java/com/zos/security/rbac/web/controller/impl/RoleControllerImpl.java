package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.*;
import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.RoleParamDetailDTO;
import com.zos.security.rbac.dto.response.base.*;
import com.zos.security.rbac.dto.response.detail.RoleDetailDTO;
import com.zos.security.rbac.mapper.base.*;
import com.zos.security.rbac.mapper.common.RelationsMapper;
import com.zos.security.rbac.mapper.detail.RoleDetailMapper;
import com.zos.security.rbac.mapper.param.RoleParamMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.support.constance.ResponseCode;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.web.controller.RoleController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Transactional
@RestController
@Api(description = "角色")
public class RoleControllerImpl implements RoleController {

	@Autowired
	RoleService roleService;

	@Override
	@ApiOperation(value = "新建角色信息")
	public ResponseDTO<RoleBaseDTO> create(@Valid @RequestBody RoleBaseDTO roleBaseDTO) {
		ResponseDTO<RoleBaseDTO> responseDTO = new ResponseDTO<RoleBaseDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(RoleBaseMapper.INSTANCE.boToDTO(roleService.create(RoleBaseMapper.INSTANCE.dtoToBO(roleBaseDTO))));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "更新角色信息")
	public ResponseDTO<RoleBaseDTO> update(@ApiParam(value = "角色主键") @PathVariable String id, @Valid @RequestBody RoleBaseDTO roleBaseDTO) throws Exception {
		RoleBaseDTO roleBaseDTOResult = RoleBaseMapper.INSTANCE.boToDTO(roleService.update(id, RoleBaseMapper.INSTANCE.dtoToBO(roleBaseDTO)));
		ResponseDTO<RoleBaseDTO> responseDTO = new ResponseDTO<RoleBaseDTO>();
		if (ConstantValidator.isValuable(roleBaseDTOResult.getId())) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(roleBaseDTOResult);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("角色信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除角色信息")
	public void delete(@ApiParam(value = "角色主键") @PathVariable String id) throws Exception {
		roleService.delete(id);
	}

	@Override
	@ApiOperation(value = "查询角色详细信息")
	public ResponseDTO<RoleDetailDTO> getInfo(@ApiParam(value = "角色主键") @PathVariable String id) throws Exception {
		ResponseDTO<RoleDetailDTO> responseDTO = new ResponseDTO<RoleDetailDTO>();
		RoleDetailDTO roleDetailDTO = RoleDetailMapper.INSTANCE.boToDTO(roleService.getInfo(id));
		if (roleDetailDTO != null) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(roleDetailDTO);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("角色信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询角色基本信息")
	public ResponseDTO<Page<RoleBaseDTO>> queryBase(RoleParamBaseDTO roleParamBaseDTO, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleBaseBO> roleBaseBOPage = roleService.queryBase(RoleParamMapper.INSTANCE.dtoToBO(roleParamBaseDTO), pageable);
		Page<RoleBaseDTO> roleBaseDTOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.boToDTO(roleBaseBOPage.getContent()), pageable, roleBaseBOPage.getTotalElements());
		ResponseDTO<Page<RoleBaseDTO>> responseDTO = new ResponseDTO<Page<RoleBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询角色详细信息")
	public ResponseDTO<Page<RoleDetailDTO>> queryDetail(RoleParamDetailDTO roleParamDetailDTO, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleDetailBO> roleDetailBOPage = roleService.queryDetail(RoleParamMapper.INSTANCE.dtoToBO(roleParamDetailDTO), pageable);
		Page<RoleDetailDTO> roleDetailDTOPage = QueryResultConverter.convert(RoleDetailMapper.INSTANCE.boToDTO(roleDetailBOPage.getContent()), pageable, roleDetailBOPage.getTotalElements());
		ResponseDTO<Page<RoleDetailDTO>> responseDTO = new ResponseDTO<Page<RoleDetailDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleDetailDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询角色关联成员")
	public ResponseDTO<Page<UserBaseDTO>> queryUser(@ApiParam(value = "角色主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<UserBaseBO> userBaseBOPage = roleService.queryUser(id, pageable);
		Page<UserBaseDTO> userBaseDTOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.boToDTO(userBaseBOPage.getContent()), pageable, userBaseBOPage.getTotalElements());
		ResponseDTO<Page<UserBaseDTO>> responseDTO = new ResponseDTO<Page<UserBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(userBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色成员")
	public ResponseDTO<Long> batchAddUser(@ApiParam(value = "角色主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleService.batchAddUser(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色关联成员")
	public void batchRemoveUser(@ApiParam(value = "角色主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleService.batchRemoveUser(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

	@Override
	@ApiOperation(value = "查询角色关联角色组")
	public ResponseDTO<Page<RoleGroupBaseDTO>> queryRoleGroup(@ApiParam(value = "角色主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleGroupBaseBO> roleGroupBaseBOPage = roleService.queryRoleGroup(id, pageable);
		Page<RoleGroupBaseDTO> roleGroupBaseDTOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupBaseBOPage.getContent()), pageable, roleGroupBaseBOPage.getTotalElements());
		ResponseDTO<Page<RoleGroupBaseDTO>> responseDTO = new ResponseDTO<Page<RoleGroupBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleGroupBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色角色组")
	public ResponseDTO<Long> batchAddRoleGroup(@ApiParam(value = "角色主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleService.batchAddRoleGroup(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色关联角色组")
	public void batchRemoveRoleGroup(@ApiParam(value = "角色主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleService.batchRemoveRoleGroup(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

	@Override
	@ApiOperation(value = "查询角色关联资源")
	public ResponseDTO<Page<ResourceBaseDTO>> queryResource(@ApiParam(value = "角色主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<ResourceBaseBO> resourceBaseBOPage = roleService.queryResource(id, pageable);
		Page<ResourceBaseDTO> resourceBaseDTOPage = QueryResultConverter.convert(ResourceBaseMapper.INSTANCE.boToDTO(resourceBaseBOPage.getContent()), pageable, resourceBaseBOPage.getTotalElements());
		ResponseDTO<Page<ResourceBaseDTO>> responseDTO = new ResponseDTO<Page<ResourceBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(resourceBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色资源")
	public ResponseDTO<Long> batchAddResource(@ApiParam(value = "角色主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleService.batchAddResource(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色关联资源")
	public void batchRemoveResource(@ApiParam(value = "角色主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleService.batchRemoveResource(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

	@Override
	@ApiOperation(value = "查询角色关联团队")
	public ResponseDTO<Page<TeamBaseDTO>> queryTeam(@ApiParam(value = "角色主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamBaseBO> teamBaseBOPage = roleService.queryTeam(id, pageable);
		Page<TeamBaseDTO> teamBaseDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamBaseBOPage.getContent()), pageable, teamBaseBOPage.getTotalElements());
		ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色团队")
	public ResponseDTO<Long> batchAddTeam(@ApiParam(value = "角色主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleService.batchAddTeam(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色关联团队")
	public void batchRemoveTeam(@ApiParam(value = "角色主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleService.batchRemoveTeam(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}
}
