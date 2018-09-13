package com.zos.security.rbac.mapper.base;

import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.domain.Team;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamBaseMapper {

    TeamBaseMapper INSTANCE = Mappers.getMapper(TeamBaseMapper.class);

    // 基本信息
    Team boToDomain(TeamBaseBO teamBaseBO);

    TeamBaseBO domainToBO(Team team);

    List<TeamBaseBO> domainToBO(List<Team> teams);

    TeamBaseBO dtoToBO(TeamBaseDTO teamBaseDTO);

    TeamBaseDTO boToDTO(TeamBaseBO teamBaseBO);

    List<TeamBaseDTO> boToDTO(List<TeamBaseBO> teamBaseBOs);
}
