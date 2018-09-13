package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.ResourceBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.ResourceDetailBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.ResourceParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.ResourceParamDetailDTO;
import com.zos.security.rbac.dto.response.base.ResourceBaseDTO;
import com.zos.security.rbac.dto.response.detail.ResourceDetailDTO;
import com.zos.security.rbac.mapper.base.ResourceBaseMapper;
import com.zos.security.rbac.mapper.detail.ResourceDetailMapper;
import com.zos.security.rbac.mapper.param.ResourceParamMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.ResourceService;
import com.zos.security.rbac.support.constance.ResponseCode;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.web.controller.ResourceController;
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
@Api(description = "资源")
public class ResourceControllerImpl implements ResourceController {

	@Autowired
	ResourceService resourceService;

	@Override
	@ApiOperation(value = "新建资源信息")
	public ResponseDTO<ResourceBaseDTO> create(@Valid @RequestBody ResourceBaseDTO resourceBaseDTO) {
		ResponseDTO<ResourceBaseDTO> responseDTO = new ResponseDTO<ResourceBaseDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(ResourceBaseMapper.INSTANCE.boToDTO(resourceService.create(ResourceBaseMapper.INSTANCE.dtoToBO(resourceBaseDTO))));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "更新资源信息")
	public ResponseDTO<ResourceBaseDTO> update(@ApiParam(value = "资源主键") @PathVariable String id, @Valid @RequestBody ResourceBaseDTO resourceBaseDTO) throws Exception {
		ResourceBaseDTO resourceBaseDTOResult = ResourceBaseMapper.INSTANCE.boToDTO(resourceService.update(id, ResourceBaseMapper.INSTANCE.dtoToBO(resourceBaseDTO)));
		ResponseDTO<ResourceBaseDTO> responseDTO = new ResponseDTO<ResourceBaseDTO>();
		if (ConstantValidator.isAvaluableId(resourceBaseDTOResult.getId())) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(resourceBaseDTOResult);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("资源信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除资源信息")
	public void delete(@ApiParam(value = "资源主键") @PathVariable String id) throws Exception {
		resourceService.delete(id);
	}

	@Override
	@ApiOperation(value = "查询资源详细信息")
	public ResponseDTO<ResourceDetailDTO> getInfo(@ApiParam(value = "资源主键") @PathVariable String id) throws Exception {
		ResponseDTO<ResourceDetailDTO> responseDTO = new ResponseDTO<ResourceDetailDTO>();
		ResourceDetailDTO resourceDetailDTO = ResourceDetailMapper.INSTANCE.boToDTO(resourceService.getInfo(id));
		if (resourceDetailDTO != null) {
			responseDTO.setCode(ResponseCode.SUCCESS);
			responseDTO.setData(resourceDetailDTO);
		} else {
			responseDTO.setCode(ResponseCode.INVALID);
			responseDTO.setMessage("资源信息不存在");
		}
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询资源基本信息")
	public ResponseDTO<Page<ResourceBaseDTO>> querySimple(ResourceParamBaseDTO resourceParamBaseDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<ResourceBaseBO> resourceSimpleBOPage = resourceService.querySimple(ResourceParamMapper.INSTANCE.dtoToBO(resourceParamBaseDTO), pageable);
		Page<ResourceBaseDTO> resourceSimpleDTOPage = QueryResultConverter.convert(ResourceBaseMapper.INSTANCE.boToDTO(resourceSimpleBOPage.getContent()), pageable, resourceSimpleBOPage.getTotalElements());
		ResponseDTO<Page<ResourceBaseDTO>> responseDTO = new ResponseDTO<Page<ResourceBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(resourceSimpleDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询资源详细信息")
	public ResponseDTO<Page<ResourceDetailDTO>> queryDetail(ResourceParamDetailDTO resourceParamDetailDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<ResourceDetailBO> resourceDetailBOPage = resourceService.queryDetail(ResourceParamMapper.INSTANCE.dtoToBO(resourceParamDetailDTO), pageable);
		Page<ResourceDetailDTO> resourceDetailDTOPage = QueryResultConverter.convert(ResourceDetailMapper.INSTANCE.boToDTO(resourceDetailBOPage.getContent()), pageable, resourceDetailBOPage.getTotalElements());
		ResponseDTO<Page<ResourceDetailDTO>> responseDTO = new ResponseDTO<Page<ResourceDetailDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(resourceDetailDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "变更归属资源")
	public void changeParent(@ApiParam(value = "父级资源主键") @PathVariable String parentId, @ApiParam(value = "子级资源主键") @PathVariable String id) throws Exception {
		resourceService.changeParent(parentId, id);
	}

	@Override
	@ApiOperation(value = "查询子级资源")
	public ResponseDTO<Page<ResourceBaseDTO>> queryByParentId(@ApiParam(value = "父级资源主键") @PathVariable String parentId, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
		Page<ResourceBaseBO> resourceSimpleBOPage = resourceService.queryByParentId(parentId, pageable);
		Page<ResourceBaseDTO> resourceSimpleDTOPage = QueryResultConverter.convert(ResourceBaseMapper.INSTANCE.boToDTO(resourceSimpleBOPage.getContent()), pageable, resourceSimpleBOPage.getTotalElements());
		ResponseDTO<Page<ResourceBaseDTO>> responseDTO = new ResponseDTO<Page<ResourceBaseDTO>>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(resourceSimpleDTOPage);
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "查询父级资源")
	public ResponseDTO<ResourceBaseDTO> queryParentById(@ApiParam(value = "子级资源主键") @PathVariable String id) throws Exception {
		ResourceBaseBO resourceBaseBO = resourceService.queryParentById(id);
		ResponseDTO<ResourceBaseDTO> responseDTO = new ResponseDTO<ResourceBaseDTO>();
		responseDTO.setCode(ResponseCode.SUCCESS);
		responseDTO.setData(ResourceBaseMapper.INSTANCE.boToDTO(resourceBaseBO));
		return responseDTO;
	}

	@Override
	@ApiOperation(value = "删除子级资源")
	public void deleteByParentId(@ApiParam(value = "父级资源主键") @PathVariable String parentId) throws Exception {
		resourceService.deleteByParentId(parentId);
	}

}
