package com.zos.security.rbac.mapper.param;

import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.bo.param.detail.UserParamDetailBO;
import com.zos.security.rbac.bo.param.info.UserParamInfoBO;
import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.UserParamDetailDTO;
import com.zos.security.rbac.dto.param.info.UserParamInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserParamMapper {
	
	UserParamMapper INSTANCE = Mappers.getMapper(UserParamMapper.class);

	// 查询条件_基本信息
    UserParamBaseBO dtoToBO(UserParamBaseDTO userParamBaseDTO);

    // 查询条件_详细信息
    UserParamDetailBO dtoToBO(UserParamDetailDTO userParamDetailDTO);

    // 查询条件_账号信息
    UserParamInfoBO dtoToBO(UserParamInfoDTO userParamInfoDTO);
	
}
