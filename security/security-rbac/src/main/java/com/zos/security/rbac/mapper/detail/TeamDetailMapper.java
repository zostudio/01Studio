package com.zos.security.rbac.mapper.detail;

import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.domain.Team;
import com.zos.security.rbac.dto.response.detail.TeamDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamDetailMapper {

    TeamDetailMapper INSTANCE = Mappers.getMapper(TeamDetailMapper.class);

    // 详细信息
    TeamDetailBO domainToBO(Team team);

    List<TeamDetailBO> domainToBO(List<Team> teams);

    TeamDetailDTO boToDTO(TeamDetailBO teamDetailBO);

    List<TeamDetailDTO> boToDTO(List<TeamDetailBO> teamDetailBOs);
}
