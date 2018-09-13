package com.zos.security.rbac.mapper.param;

import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.param.base.TeamParamBaseBO;
import com.zos.security.rbac.dto.param.detail.TeamParamDetailDTO;
import com.zos.security.rbac.dto.param.base.TeamParamBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamParamMapper {
	
	TeamParamMapper INSTANCE = Mappers.getMapper(TeamParamMapper.class);

	// 查询条件_基本信息
    TeamParamBaseBO dtoToBO(TeamParamBaseDTO teamParamBaseDTO);

    // 查询条件_详细信息
    TeamParamDetailBO dtoToBO(TeamParamDetailDTO teamParamDetailDTO);
	
}
