package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.param.simple.TeamParamSimpleBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.bo.resopnse.simple.TeamSimpleBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
	
	TeamSimpleBO create(TeamSimpleBO teamSimpleBO);
	
	TeamSimpleBO update(Long id, TeamSimpleBO teamSimpleBO);
	
	void delete(Long id);
	
	TeamDetailBO getInfo(Long id);

	Page<TeamSimpleBO> querySimple(TeamParamSimpleBO teamParamSimpleBO, Pageable pageable);

	Page<TeamDetailBO> queryDetail(TeamParamDetailBO teamParamDetailBO, Pageable pageable);

	void changeParent(Long parentId, Long id) throws Exception;

	Page<TeamSimpleBO> queryByParentId(Long parentId, Pageable pageable) throws Exception;

	TeamSimpleBO queryParentById(Long id) throws Exception;

	void deleteByParentId(Long parentId) throws Exception;
}
