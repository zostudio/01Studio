package com.zos.security.rbac.mapper.param;

import com.zos.security.rbac.bo.param.base.ResourceParamBaseBO;
import com.zos.security.rbac.bo.param.detail.ResourceParamDetailBO;
import com.zos.security.rbac.dto.param.base.ResourceParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.ResourceParamDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceParamMapper {
	
	ResourceParamMapper INSTANCE = Mappers.getMapper(ResourceParamMapper.class);

	// 查询条件_基本信息
    ResourceParamBaseBO dtoToBO(ResourceParamBaseDTO resourceParamBaseDTO);

    // 查询条件_详细信息
    ResourceParamDetailBO dtoToBO(ResourceParamDetailDTO resourceParamDetailDTO);
	
}
