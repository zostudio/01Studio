package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.param.base.TeamParamBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
	
	TeamBaseBO create(TeamBaseBO teamBaseBO);
	
	TeamBaseBO update(String id, TeamBaseBO teamBaseBO);
	
	void delete(String id);
	
	TeamDetailBO getInfo(String id);

	Page<TeamBaseBO> querySimple(TeamParamBaseBO teamParamBaseBO, Pageable pageable);

	Page<TeamDetailBO> queryDetail(TeamParamDetailBO teamParamDetailBO, Pageable pageable);

	void changeParent(String parentId, String id) throws Exception;

	Page<TeamBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception;

	TeamBaseBO queryParentById(String id) throws Exception;

	void deleteByParentId(String parentId) throws Exception;
}
