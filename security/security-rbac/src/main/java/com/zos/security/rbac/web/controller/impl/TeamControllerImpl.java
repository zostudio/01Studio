package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.detail.TeamParamDetailDTO;
import com.zos.security.rbac.dto.param.base.TeamParamBaseDTO;
import com.zos.security.rbac.dto.response.detail.TeamDetailDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import com.zos.security.rbac.mapper.detail.TeamDetailMapper;
import com.zos.security.rbac.mapper.param.TeamParamMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
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
	public ResponseDTO<Page<TeamBaseDTO>> querySimple(TeamParamBaseDTO teamParamBaseDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamBaseBO> teamSimpleBOPage = teamService.querySimple(TeamParamMapper.INSTANCE.dtoToBO(teamParamBaseDTO), pageable);
		Page<TeamBaseDTO> teamSimpleDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamSimpleBOPage.getContent()), pageable, teamSimpleBOPage.getTotalElements());
		ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamSimpleDTOPage);
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
        Page<TeamBaseBO> teamSimpleBOPage = teamService.queryByParentId(parentId, pageable);
        Page<TeamBaseDTO> teamSimpleDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamSimpleBOPage.getContent()), pageable, teamSimpleBOPage.getTotalElements());
        ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(teamSimpleDTOPage);
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

}
