package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.bo.resopnse.simple.TeamSimpleBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.detail.TeamParamDetailDTO;
import com.zos.security.rbac.dto.param.simple.TeamParamSimpleDTO;
import com.zos.security.rbac.dto.response.detail.TeamDetailDTO;
import com.zos.security.rbac.dto.response.simple.TeamSimpleDTO;
import com.zos.security.rbac.mapper.detail.TeamDetailMapper;
import com.zos.security.rbac.mapper.param.TeamParamMapper;
import com.zos.security.rbac.mapper.simple.TeamSimpleMapper;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "团队接口")
public class TeamControllerImpl implements TeamController {
	
	@Autowired
	TeamService teamService;

	@Override
	@ApiOperation(value = "新建团队信息")
	public ResponseDTO<TeamSimpleDTO> create(@RequestBody TeamSimpleDTO teamSimpleDTO) {
		ResponseDTO<TeamSimpleDTO> responseDTO = new ResponseDTO<TeamSimpleDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(TeamSimpleMapper.INSTANCE.boToDTO4Simple(teamService.create(TeamSimpleMapper.INSTANCE.dtoToBO4Simple(teamSimpleDTO))));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "更新团队信息")
	public ResponseDTO<TeamSimpleDTO> update(@ApiParam(value = "团队主键") @PathVariable Long id, @RequestBody TeamSimpleDTO teamSimpleDTO) throws Exception {
		TeamSimpleDTO teamSimpleDTOResult = TeamSimpleMapper.INSTANCE.boToDTO4Simple(teamService.update(id, TeamSimpleMapper.INSTANCE.dtoToBO4Simple(teamSimpleDTO)));
		ResponseDTO<TeamSimpleDTO> responseDTO = new ResponseDTO<TeamSimpleDTO>();
		if (ConstantValidator.isAvaluableId(teamSimpleDTOResult.getId())) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(teamSimpleDTOResult);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("团队信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除团队信息")
	public void delete(@ApiParam(value = "团队主键") @PathVariable Long id) throws Exception {
		teamService.delete(id);
	}

	@Override
	@ApiOperation(value = "查询团队详细信息")
	public ResponseDTO<TeamDetailDTO> getInfo(@ApiParam(value = "团队主键") @PathVariable Long id) throws Exception {
		ResponseDTO<TeamDetailDTO> responseDTO = new ResponseDTO<TeamDetailDTO>();
		TeamDetailDTO teamDetailDTO = TeamDetailMapper.INSTANCE.boToDTO4Detail(teamService.getInfo(id));
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
	public ResponseDTO<Page<TeamSimpleDTO>> querySimple(TeamParamSimpleDTO teamParamSimpleDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamSimpleBO> teamSimpleBOPage = teamService.querySimple(TeamParamMapper.INSTANCE.dtoToBO(teamParamSimpleDTO), pageable);
		Page<TeamSimpleDTO> teamSimpleDTOPage = QueryResultConverter.convert(TeamSimpleMapper.INSTANCE.boToDTO4Simple(teamSimpleBOPage.getContent()), pageable, teamSimpleBOPage.getTotalElements());
		ResponseDTO<Page<TeamSimpleDTO>> responseDTO = new ResponseDTO<Page<TeamSimpleDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamSimpleDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询团队详细信息")
	public ResponseDTO<Page<TeamDetailDTO>> queryDetail(TeamParamDetailDTO teamParamDetailDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<TeamDetailBO> teamDetailBOPage = teamService.queryDetail(TeamParamMapper.INSTANCE.dtoToBO(teamParamDetailDTO), pageable);
		Page<TeamDetailDTO> teamDetailDTOPage = QueryResultConverter.convert(TeamDetailMapper.INSTANCE.boToDTO4Detail(teamDetailBOPage.getContent()), pageable, teamDetailBOPage.getTotalElements());
		ResponseDTO<Page<TeamDetailDTO>> responseDTO = new ResponseDTO<Page<TeamDetailDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(teamDetailDTOPage);
		return responseDTO;
	}

	@Override
    @ApiOperation(value = "变更归属团队")
	public void changeParent(@ApiParam(value = "父级团队主键") @PathVariable Long parentId, @ApiParam(value = "子级团队主键") @PathVariable Long id) throws Exception {
		teamService.changeParent(parentId, id);
	}

	@Override
    @ApiOperation(value = "查询子级团队")
	public ResponseDTO<Page<TeamSimpleDTO>> queryByParentId(@ApiParam(value = "父级团队主键") @PathVariable Long parentId, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<TeamSimpleBO> teamSimpleBOPage = teamService.queryByParentId(parentId, pageable);
        Page<TeamSimpleDTO> teamSimpleDTOPage = QueryResultConverter.convert(TeamSimpleMapper.INSTANCE.boToDTO4Simple(teamSimpleBOPage.getContent()), pageable, teamSimpleBOPage.getTotalElements());
        ResponseDTO<Page<TeamSimpleDTO>> responseDTO = new ResponseDTO<Page<TeamSimpleDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(teamSimpleDTOPage);
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "查询父级团队")
	public ResponseDTO<TeamSimpleDTO> queryParentById(@ApiParam(value = "子级团队主键") @PathVariable Long id) throws Exception {
        TeamSimpleBO teamSimpleBO = teamService.queryParentById(id);
        ResponseDTO<TeamSimpleDTO> responseDTO = new ResponseDTO<TeamSimpleDTO>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(TeamSimpleMapper.INSTANCE.boToDTO4Simple(teamSimpleBO));
        return responseDTO;
	}

	@Override
    @ApiOperation(value = "删除子级团队")
	public void deleteByParentId(@ApiParam(value = "父级团队主键") @PathVariable Long parentId) throws Exception {
        teamService.deleteByParentId(parentId);
	}

}
