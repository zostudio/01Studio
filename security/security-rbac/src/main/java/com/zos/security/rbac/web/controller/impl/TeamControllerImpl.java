package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
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
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.base.RoleGroupBaseMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
import com.zos.security.rbac.mapper.common.RelationsMapper;
import com.zos.security.rbac.mapper.detail.TeamDetailMapper;
import com.zos.security.rbac.mapper.param.TeamParamMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.TeamService;
import com.zos.security.rbac.support.constance.ResponseCode;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.web.controller.TeamController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Transactional
@RestController
@Api(description = "团队")
public class TeamControllerImpl implements TeamController {
	
	@Autowired
	TeamService teamService;

	@Override
	@ApiOperation(value = "新建团队信息")
	public ResponseDTO<TeamBaseDTO> create(@Valid @RequestBody TeamBaseDTO teamBaseDTO) {
		ResponseDTO<TeamBaseDTO> responseDTO = new ResponseDTO<TeamBaseDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(TeamBaseMapper.INSTANCE.boToDTO(teamService.create(TeamBaseMapper.INSTANCE.dtoToBO(teamBaseDTO))));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "更新团队信息")
	public ResponseDTO<TeamBaseDTO> update(@ApiParam(value = "团队主键") @PathVariable String id, @Valid @RequestBody TeamBaseDTO teamBaseDTO) throws Exception {
		TeamBaseDTO teamBaseDTOResult = TeamBaseMapper.INSTANCE.boToDTO(teamService.update(id, TeamBaseMapper.INSTANCE.dtoToBO(teamBaseDTO)));
		ResponseDTO<TeamBaseDTO> responseDTO = new ResponseDTO<TeamBaseDTO>();
		if (ConstantValidator.isValuable(teamBaseDTOResult.getId())) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(teamBaseDTOResult);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("团队信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除团队信息")
	public void delete(@ApiParam(value = "团队主键") @PathVariable String id) throws Exception {
		teamService.delete(id);
	}

	@Override
	@ApiOperation(value = "查询团队详细信息")
	public ResponseDTO<TeamDetailDTO> getInfo(@ApiParam(value = "团队主键") @PathVariable String id) throws Exception {
		ResponseDTO<TeamDetailDTO> responseDTO = new ResponseDTO<TeamDetailDTO>();
		TeamDetailDTO teamDetailDTO = TeamDetailMapper.INSTANCE.boToDTO(teamService.getInfo(id));
		if (teamDetailDTO != null) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(teamDetailDTO);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("团队信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询团队基本信息")
	public ResponseDTO<Page<TeamBaseDTO>> queryBase(TeamParamBaseDTO teamParamBaseDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamBaseBO> teamBaseBOPage = teamService.queryBase(TeamParamMapper.INSTANCE.dtoToBO(teamParamBaseDTO), pageable);
		Page<TeamBaseDTO> teamBaseDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamBaseBOPage.getContent()), pageable, teamBaseBOPage.getTotalElements());
		ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamBaseDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询团队详细信息")
	public ResponseDTO<Page<TeamDetailDTO>> queryDetail(TeamParamDetailDTO teamParamDetailDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamDetailBO> teamDetailBOPage = teamService.queryDetail(TeamParamMapper.INSTANCE.dtoToBO(teamParamDetailDTO), pageable);
		Page<TeamDetailDTO> teamDetailDTOPage = QueryResultConverter.convert(TeamDetailMapper.INSTANCE.boToDTO(teamDetailBOPage.getContent()), pageable, teamDetailBOPage.getTotalElements());
		ResponseDTO<Page<TeamDetailDTO>> responseDTO = new ResponseDTO<Page<TeamDetailDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamDetailDTOPage);
		return responseDTO;
	}

	@Override
    @ApiOperation(value = "变更归属团队")
	public void changeParent(@ApiParam(value = "父级团队主键") @PathVariable String parentId, @ApiParam(value = "子级团队主键") @PathVariable String id) throws Exception {
		teamService.changeParent(parentId, id);
	}

	@Override
    @ApiOperation(value = "查询子级团队")
	public ResponseDTO<Page<TeamBaseDTO>> queryByParentId(@ApiParam(value = "父级团队主键") @PathVariable String parentId, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<TeamBaseBO> teamBaseBOPage = teamService.queryByParentId(parentId, pageable);
        Page<TeamBaseDTO> teamBaseDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamBaseBOPage.getContent()), pageable, teamBaseBOPage.getTotalElements());
        ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(teamBaseDTOPage);
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "查询父级团队")
	public ResponseDTO<TeamBaseDTO> queryParentById(@ApiParam(value = "子级团队主键") @PathVariable String id) throws Exception {
        TeamBaseBO teamBaseBO = teamService.queryParentById(id);
        ResponseDTO<TeamBaseDTO> responseDTO = new ResponseDTO<TeamBaseDTO>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(TeamBaseMapper.INSTANCE.boToDTO(teamBaseBO));
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "删除子级团队")
	public void deleteByParentId(@ApiParam(value = "父级团队主键") @PathVariable String parentId) throws Exception {
        teamService.deleteByParentId(parentId);
	}

	@Override
    @ApiOperation(value = "查询团队成员")
	public ResponseDTO<Page<UserBaseDTO>> queryUser(@ApiParam(value = "团队主键") @PathVariable String id, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<UserBaseBO> userBaseBOPage = teamService.queryUser(id, pageable);
        Page<UserBaseDTO> userBaseDTOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.boToDTO(userBaseBOPage.getContent()), pageable, userBaseBOPage.getTotalElements());
        ResponseDTO<Page<UserBaseDTO>> responseDTO = new ResponseDTO<Page<UserBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userBaseDTOPage);
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "批量添加团队成员")
	public ResponseDTO<Long> batchAddUser(@ApiParam(value = "团队主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
        Long result = teamService.batchAddUser(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
        ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(result);
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "批量移除团队成员")
	public void batchRemoveUser(@ApiParam(value = "团队主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
        teamService.batchRemoveUser(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

    @Override
    @ApiOperation(value = "查询团队角色")
    public ResponseDTO<Page<RoleBaseDTO>> queryRole(@ApiParam(value = "团队主键") @PathVariable String id, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<RoleBaseBO> roleBaseBOPage = teamService.queryRole(id, pageable);
        Page<RoleBaseDTO> roleBaseDTOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.boToDTO(roleBaseBOPage.getContent()), pageable, roleBaseBOPage.getTotalElements());
        ResponseDTO<Page<RoleBaseDTO>> responseDTO = new ResponseDTO<Page<RoleBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(roleBaseDTOPage);
        return responseDTO;
    }

	@Override
    @ApiOperation(value = "批量添加团队角色")
	public ResponseDTO<Long> batchAddRole(@ApiParam(value = "团队主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
        Long result = teamService.batchAddRole(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
        ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(result);
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "批量移除团队角色")
	public void batchRemoveRole(@ApiParam(value = "团队主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
        teamService.batchRemoveRole(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

    @Override
    @ApiOperation(value = "查询团队角色组")
    public ResponseDTO<Page<RoleGroupBaseDTO>> queryRoleGroup(@ApiParam(value = "团队主键") @PathVariable String id, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<RoleGroupBaseBO> roleGroupBaseBOPage = teamService.queryRoleGroup(id, pageable);
        Page<RoleGroupBaseDTO> roleGroupBaseDTOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupBaseBOPage.getContent()), pageable, roleGroupBaseBOPage.getTotalElements());
        ResponseDTO<Page<RoleGroupBaseDTO>> responseDTO = new ResponseDTO<Page<RoleGroupBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(roleGroupBaseDTOPage);
        return responseDTO;
    }

	@Override
    @ApiOperation(value = "批量添加团队角色组")
	public ResponseDTO<Long> batchAddRoleGroup(@ApiParam(value = "团队主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
        Long result = teamService.batchAddRoleGroup(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
        ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(result);
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "批量移除团队角色组")
	public void batchRemoveRoleGroup(@ApiParam(value = "团队主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
        teamService.batchRemoveRoleGroup(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
	}

}
