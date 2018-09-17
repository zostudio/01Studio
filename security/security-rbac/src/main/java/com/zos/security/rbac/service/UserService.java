package com.zos.security.rbac.service;

import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.UserParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.bo.resopnse.info.UserInfoBO;
import com.zos.security.rbac.domain.QUser;
import com.zos.security.rbac.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	UserInfoBO create(UserInfoBO userInfoBO);

	UserInfoBO update(String id, UserInfoBO userInfoBO);

	void delete(String id);

	UserDetailBO getInfo(String id);

	Page<UserBaseBO> queryBase(UserParamBaseBO userParamBaseBO, Pageable pageable);

	Page<UserDetailBO> queryDetail(UserParamDetailBO userParamDetailBO, Pageable pageable);

    QBean<User> getBaseBean(QUser _Q_User);

	void orderBy(Pageable pageable, QUser _Q_User, JPAQuery<User> userJPAQuery);

    Boolean existByUsername(String username);

    Boolean existByEmail(String email);

    Boolean existByPhone(String phone);

    User signIn(String username);

	Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception;

	Long batchAddRole(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRole(String id,  RelationsBO relationsBO) throws Exception;

	Page<RoleGroupBaseBO> queryRoleGroup(String id, Pageable pageable) throws Exception;

	Long batchAddRoleGroup(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRoleGroup(String id,  RelationsBO relationsBO) throws Exception;

	Page<TeamBaseBO> queryTeam(String id, Pageable pageable) throws Exception;

	Long batchAddTeam(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveTeam(String id,  RelationsBO relationsBO) throws Exception;
}
