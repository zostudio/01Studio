package com.zos.security.rbac.mapper.simple;

import com.zos.security.rbac.bo.resopnse.simple.TeamSimpleBO;
import com.zos.security.rbac.domain.Team;
import com.zos.security.rbac.dto.response.simple.TeamSimpleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamSimpleMapper {

    TeamSimpleMapper INSTANCE = Mappers.getMapper(TeamSimpleMapper.class);

    // 基本信息
    Team boToDomain4Simple(TeamSimpleBO teamSimpleBO);

    TeamSimpleBO domainToBO4Simple(Team team);

    List<TeamSimpleBO> domainToBO4Simple(List<Team> teams);

    TeamSimpleBO dtoToBO4Simple(TeamSimpleDTO teamSimpleDTO);

    TeamSimpleDTO boToDTO4Simple(TeamSimpleBO teamSimpleBO);

    List<TeamSimpleDTO> boToDTO4Simple(List<TeamSimpleBO> teamSimpleBOs);
}
