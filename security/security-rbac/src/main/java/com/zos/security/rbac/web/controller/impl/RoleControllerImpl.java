package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.RoleParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.RoleParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.detail.RoleDetailDTO;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
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
		if (ConstantValidator.isAvaluableId(roleBaseDTOResult.getId())) {
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
	public ResponseDTO<Page<RoleBaseDTO>> querySimple(RoleParamBaseDTO roleParamBaseDTO, @PageableDefault(direction = Sort.Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<RoleBaseBO> roleSimpleBOPage = roleService.querySimple(RoleParamMapper.INSTANCE.dtoToBO(roleParamBaseDTO), pageable);
		Page<RoleBaseDTO> roleSimpleDTOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.boToDTO(roleSimpleBOPage.getContent()), pageable, roleSimpleBOPage.getTotalElements());
		ResponseDTO<Page<RoleBaseDTO>> responseDTO = new ResponseDTO<Page<RoleBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(roleSimpleDTOPage);
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
}
