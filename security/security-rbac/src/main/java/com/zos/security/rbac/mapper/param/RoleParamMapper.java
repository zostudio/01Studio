package com.zos.security.rbac.mapper.param;

import com.zos.security.rbac.bo.param.base.RoleParamBaseBO;
import com.zos.security.rbac.bo.param.detail.RoleParamDetailBO;
import com.zos.security.rbac.dto.param.base.RoleParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.RoleParamDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleParamMapper {
	
	RoleParamMapper INSTANCE = Mappers.getMapper(RoleParamMapper.class);

	// 查询条件_基本信息
    RoleParamBaseBO dtoToBO(RoleParamBaseDTO roleParamBaseDTO);

    // 查询条件_详细信息
    RoleParamDetailBO dtoToBO(RoleParamDetailDTO roleParamDetailDTO);
	
}
