package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.param.simple.TeamParamSimpleBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.bo.resopnse.simple.TeamSimpleBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PutMapping;

public interface TeamService {
	
	public TeamSimpleBO create(TeamSimpleBO teamSimpleBO);
	
	public TeamSimpleBO update(Long id, TeamSimpleBO teamSimpleBO);
	
	public void delete(Long id);
	
	public TeamDetailBO getInfo(Long id);

	public Page<TeamSimpleBO> querySimple(TeamParamSimpleBO teamParamSimpleBO, Pageable pageable);

	public Page<TeamDetailBO> queryDetail(TeamParamDetailBO teamParamDetailBO, Pageable pageable);@PutMapping("/parent/{parentId:\\d+}/{id:\\d+}")

	public void changeParent(Long parentId, Long id) throws Exception;

	public Page<TeamSimpleBO> queryByParentId(Long parentId, Pageable pageable) throws Exception;

	public TeamSimpleBO queryParentById(Long id) throws Exception;

	public void deleteByParentId(Long parentId) throws Exception;
}
