package com.zos.security.rbac.service;

import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.zos.security.rbac.bo.param.base.RoleGroupParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.RoleGroupParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleGroupDetailBO;
import com.zos.security.rbac.domain.QRoleGroup;
import com.zos.security.rbac.domain.RoleGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleGroupService {

	RoleGroupBaseBO create(RoleGroupBaseBO roleGroupBaseBO);

	RoleGroupBaseBO update(String id, RoleGroupBaseBO roleGroupBaseBO);

	void delete(String id);

	RoleGroupDetailBO getInfo(String id);

	Page<RoleGroupBaseBO> queryBase(RoleGroupParamBaseBO roleGroupParamBaseBO, Pageable pageable);

	Page<RoleGroupDetailBO> queryDetail(RoleGroupParamDetailBO roleGroupParamDetailBO, Pageable pageable);

	QBean<RoleGroup> getBaseBean(QRoleGroup _Q_RoleGroup);

	void orderBy(Pageable pageable, QRoleGroup _Q_RoleGroup, JPAQuery<RoleGroup> roleGroupJPAQuery);

	void changeParent(String parentId, String id) throws Exception;

	Page<RoleGroupBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception;

	RoleGroupBaseBO queryParentById(String id) throws Exception;

	void deleteByParentId(String parentId) throws Exception;

	Page<UserBaseBO> queryUser(String id, Pageable pageable) throws Exception;

	Long batchAddUser(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveUser(String id,  RelationsBO relationsBO) throws Exception;

	Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception;

	Long batchAddRole(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRole(String id,  RelationsBO relationsBO) throws Exception;

	Page<TeamBaseBO> queryTeam(String id, Pageable pageable) throws Exception;

	Long batchAddTeam(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveTeam(String id,  RelationsBO relationsBO) throws Exception;
}
