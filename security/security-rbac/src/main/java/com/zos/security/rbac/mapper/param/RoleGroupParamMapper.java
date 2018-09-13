package com.zos.security.rbac.mapper.param;

import com.zos.security.rbac.bo.param.base.RoleGroupParamBaseBO;
import com.zos.security.rbac.bo.param.detail.RoleGroupParamDetailBO;
import com.zos.security.rbac.dto.param.base.RoleGroupParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.RoleGroupParamDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleGroupParamMapper {
	
	RoleGroupParamMapper INSTANCE = Mappers.getMapper(RoleGroupParamMapper.class);

	// 查询条件_基本信息
    RoleGroupParamBaseBO dtoToBO(RoleGroupParamBaseDTO roleGroupParamBaseDTO);

    // 查询条件_详细信息
    RoleGroupParamDetailBO dtoToBO(RoleGroupParamDetailDTO roleGroupParamDetailDTO);
	
}
