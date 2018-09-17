package com.zos.security.rbac.mapper.common;

import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelationsMapper {

    RelationsMapper INSTANCE = Mappers.getMapper(RelationsMapper.class);

    RelationsBO dtoToBO(RelationsAddDTO relationsAddDTO);

    RelationsBO dtoToBO(RelationsRemoveDTO relationsRemoveDTO);
}
