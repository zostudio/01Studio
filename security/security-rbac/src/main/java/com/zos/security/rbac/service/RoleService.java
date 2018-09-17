package com.zos.security.rbac.service;

import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.zos.security.rbac.bo.param.base.RoleParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.RoleParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.*;
import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import com.zos.security.rbac.domain.QRole;
import com.zos.security.rbac.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

	RoleBaseBO create(RoleBaseBO roleBaseBO);

	RoleBaseBO update(String id, RoleBaseBO roleBaseBO);

	void delete(String id);

	RoleDetailBO getInfo(String id);

	Page<RoleBaseBO> queryBase(RoleParamBaseBO roleParamBaseBO, Pageable pageable);

	Page<RoleDetailBO> queryDetail(RoleParamDetailBO roleParamDetailBO, Pageable pageable);

	QBean<Role> getBaseBean(QRole _Q_Role);

	void orderBy(Pageable pageable, QRole _Q_Role, JPAQuery<Role> roleJPAQuery);

	Page<UserBaseBO> queryUser(String id, Pageable pageable) throws Exception;

	Long batchAddUser(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveUser(String id,  RelationsBO relationsBO) throws Exception;

	Page<RoleGroupBaseBO> queryRoleGroup(String id, Pageable pageable) throws Exception;

	Long batchAddRoleGroup(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRoleGroup(String id,  RelationsBO relationsBO) throws Exception;

	Page<ResourceBaseBO> queryResource(String id, Pageable pageable) throws Exception;

	Long batchAddResource(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveResource(String id,  RelationsBO relationsBO) throws Exception;

	Page<TeamBaseBO> queryTeam(String id, Pageable pageable) throws Exception;

	Long batchAddTeam(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveTeam(String id,  RelationsBO relationsBO) throws Exception;
}
