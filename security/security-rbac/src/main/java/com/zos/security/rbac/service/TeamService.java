package com.zos.security.rbac.service;

import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.zos.security.rbac.bo.param.base.TeamParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.domain.QTeam;
import com.zos.security.rbac.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
	
	TeamBaseBO create(TeamBaseBO teamBaseBO);
	
	TeamBaseBO update(String id, TeamBaseBO teamBaseBO);
	
	void delete(String id);
	
	TeamDetailBO getInfo(String id);

	Page<TeamBaseBO> queryBase(TeamParamBaseBO teamParamBaseBO, Pageable pageable);

	Page<TeamDetailBO> queryDetail(TeamParamDetailBO teamParamDetailBO, Pageable pageable);

	QBean<Team> getBaseBean(QTeam _Q_QTeam );

	void orderBy(Pageable pageable, QTeam  _Q_QTeam , JPAQuery<Team > teamJPAQuery);

	void changeParent(String parentId, String id) throws Exception;

	Page<TeamBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception;

	TeamBaseBO queryParentById(String id) throws Exception;

	void deleteByParentId(String parentId) throws Exception;

	Page<UserBaseBO> queryUser(String id, Pageable pageable) throws Exception;

	Long batchAddUser(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveUser(String id,  RelationsBO relationsBO) throws Exception;

	Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception;

	Long batchAddRole(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRole(String id,  RelationsBO relationsBO) throws Exception;

	Page<RoleGroupBaseBO> queryRoleGroup(String id, Pageable pageable) throws Exception;

	Long batchAddRoleGroup(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRoleGroup(String id,  RelationsBO relationsBO) throws Exception;
}
