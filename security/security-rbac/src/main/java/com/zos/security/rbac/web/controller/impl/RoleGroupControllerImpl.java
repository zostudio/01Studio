package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleGroupDetailBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleGroupParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.RoleGroupParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import com.zos.security.rbac.dto.response.detail.RoleGroupDetailDTO;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.base.RoleGroupBaseMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
import com.zos.security.rbac.mapper.common.RelationsMapper;
import com.zos.security.rbac.mapper.detail.RoleGroupDetailMapper;
import com.zos.security.rbac.mapper.param.RoleGroupParamMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleGroupService;
import com.zos.security.rbac.support.constance.ResponseCode;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.web.controller.RoleGroupController;
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
@Api(description = "角色组")
public class RoleGroupControllerImpl implements RoleGroupController {

	@Autowired
	RoleGroupService roleGroupService;

	@Override
	@ApiOperation(value = "新建角色组信息")
	public ResponseDTO<RoleGroupBaseDTO> create(@Valid @RequestBody RoleGroupBaseDTO roleGroupBaseDTO) {
		ResponseDTO<RoleGroupBaseDTO> responseDTO = new ResponseDTO<RoleGroupBaseDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupService.create(RoleGroupBaseMapper.INSTANCE.dtoToBO(roleGroupBaseDTO))));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "更新角色组信息")
	public ResponseDTO<RoleGroupBaseDTO> update(@ApiParam(value = "角色组主键") @PathVariable String id, @Valid @RequestBody RoleGroupBaseDTO roleGroupBaseDTO) throws Exception {
		RoleGroupBaseDTO roleGroupBaseDTOResult = RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupService.update(id, RoleGroupBaseMapper.INSTANCE.dtoToBO(roleGroupBaseDTO)));
		ResponseDTO<RoleGroupBaseDTO> responseDTO = new ResponseDTO<RoleGroupBaseDTO>();
		if (ConstantValidator.isValuable(roleGroupBaseDTOResult.getId())) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(roleGroupBaseDTOResult);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("角色组信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除角色组信息")
	public void delete(@ApiParam(value = "角色组主键") @PathVariable String id) throws Exception {
		roleGroupService.delete(id);
	}

	@Override
	@ApiOperation(value = "查询角色组详细信息")
	public ResponseDTO<RoleGroupDetailDTO> getInfo(@ApiParam(value = "角色组主键") @PathVariable String id) throws Exception {
		ResponseDTO<RoleGroupDetailDTO> responseDTO = new ResponseDTO<RoleGroupDetailDTO>();
		RoleGroupDetailDTO roleGroupDetailDTO = RoleGroupDetailMapper.INSTANCE.boToDTO(roleGroupService.getInfo(id));
		if (roleGroupDetailDTO != null) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(roleGroupDetailDTO);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("角色组信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询角色组基本信息")
	public ResponseDTO<Page<RoleGroupBaseDTO>> queryBase(RoleGroupParamBaseDTO roleGroupParamBaseDTO, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleGroupBaseBO> roleGroupBaseBOPage = roleGroupService.queryBase(RoleGroupParamMapper.INSTANCE.dtoToBO(roleGroupParamBaseDTO), pageable);
		Page<RoleGroupBaseDTO> roleGroupBaseDTOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupBaseBOPage.getContent()), pageable, roleGroupBaseBOPage.getTotalElements());
		ResponseDTO<Page<RoleGroupBaseDTO>> responseDTO = new ResponseDTO<Page<RoleGroupBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleGroupBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询角色组详细信息")
	public ResponseDTO<Page<RoleGroupDetailDTO>> queryDetail(RoleGroupParamDetailDTO roleGroupParamDetailDTO, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleGroupDetailBO> roleGroupDetailBOPage = roleGroupService.queryDetail(RoleGroupParamMapper.INSTANCE.dtoToBO(roleGroupParamDetailDTO), pageable);
		Page<RoleGroupDetailDTO> roleGroupDetailDTOPage = QueryResultConverter.convert(RoleGroupDetailMapper.INSTANCE.boToDTO(roleGroupDetailBOPage.getContent()), pageable, roleGroupDetailBOPage.getTotalElements());
		ResponseDTO<Page<RoleGroupDetailDTO>> responseDTO = new ResponseDTO<Page<RoleGroupDetailDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleGroupDetailDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "变更归属角色组")
	public void changeParent(@ApiParam(value = "父级角色组主键") @PathVariable String parentId, @ApiParam(value = "子级角色组主键") @PathVariable String id) throws Exception {
		roleGroupService.changeParent(parentId, id);
	}

	@Override
	@ApiOperation(value = "查询子级角色组")
	public ResponseDTO<Page<RoleGroupBaseDTO>> queryByParentId(@ApiParam(value = "父级角色组主键") @PathVariable String parentId, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleGroupBaseBO> roleGroupBaseBOPage = roleGroupService.queryByParentId(parentId, pageable);
		Page<RoleGroupBaseDTO> roleGroupBaseDTOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupBaseBOPage.getContent()), pageable, roleGroupBaseBOPage.getTotalElements());
		ResponseDTO<Page<RoleGroupBaseDTO>> responseDTO = new ResponseDTO<Page<RoleGroupBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleGroupBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询父级角色组")
	public ResponseDTO<RoleGroupBaseDTO> queryParentById(@ApiParam(value = "子级角色组主键") @PathVariable String id) throws Exception {
		RoleGroupBaseBO roleGroupBaseBO = roleGroupService.queryParentById(id);
		ResponseDTO<RoleGroupBaseDTO> responseDTO = new ResponseDTO<RoleGroupBaseDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupBaseBO));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除子级角色组")
	public void deleteByParentId(@ApiParam(value = "父级角色组主键") @PathVariable String parentId) throws Exception {
		roleGroupService.deleteByParentId(parentId);
	}

	@Override
	@ApiOperation(value = "查询角色组关联成员")
	public ResponseDTO<Page<UserBaseDTO>> queryUser(@ApiParam(value = "角色组主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<UserBaseBO> userBaseBOPage = roleGroupService.queryUser(id, pageable);
		Page<UserBaseDTO> userBaseDTOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.boToDTO(userBaseBOPage.getContent()), pageable, userBaseBOPage.getTotalElements());
		ResponseDTO<Page<UserBaseDTO>> responseDTO = new ResponseDTO<Page<UserBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(userBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色组成员")
	public ResponseDTO<Long> batchAddUser(@ApiParam(value = "角色组主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleGroupService.batchAddUser(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色组关联成员")
	public void batchRemoveUser(@ApiParam(value = "角色组主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleGroupService.batchRemoveUser(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

	@Override
	@ApiOperation(value = "查询角色组关联角色")
	public ResponseDTO<Page<RoleBaseDTO>> queryRole(@ApiParam(value = "角色组主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleBaseBO> roleBaseBOPage = roleGroupService.queryRole(id, pageable);
		Page<RoleBaseDTO> roleBaseDTOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.boToDTO(roleBaseBOPage.getContent()), pageable, roleBaseBOPage.getTotalElements());
		ResponseDTO<Page<RoleBaseDTO>> responseDTO = new ResponseDTO<Page<RoleBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色组角色")
	public ResponseDTO<Long> batchAddRole(@ApiParam(value = "角色组主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleGroupService.batchAddRole(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色组关联角色")
	public void batchRemoveRole(@ApiParam(value = "角色组主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleGroupService.batchRemoveRole(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

	@Override
	@ApiOperation(value = "查询角色组关联团队")
	public ResponseDTO<Page<TeamBaseDTO>> queryTeam(@ApiParam(value = "角色组主键") @PathVariable String id, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamBaseBO> teamBaseBOPage = roleGroupService.queryTeam(id, pageable);
		Page<TeamBaseDTO> teamBaseDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamBaseBOPage.getContent()), pageable, teamBaseBOPage.getTotalElements());
		ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量关联角色组团队")
	public ResponseDTO<Long> batchAddTeam(@ApiParam(value = "角色组主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
		Long result = roleGroupService.batchAddTeam(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
		ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(result);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "批量移除角色组关联团队")
	public void batchRemoveTeam(@ApiParam(value = "角色组主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
		roleGroupService.batchRemoveTeam(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}
}
