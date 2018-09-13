package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.base.RoleGroupParamBaseBO;
import com.zos.security.rbac.bo.param.detail.RoleGroupParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleGroupDetailBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleGroupService {

	RoleGroupBaseBO create(RoleGroupBaseBO roleGroupBaseBO);

	RoleGroupBaseBO update(String id, RoleGroupBaseBO roleGroupBaseBO);

	void delete(String id);

	RoleGroupDetailBO getInfo(String id);

	Page<RoleGroupBaseBO> querySimple(RoleGroupParamBaseBO roleGroupParamBaseBO, Pageable pageable);

	Page<RoleGroupDetailBO> queryDetail(RoleGroupParamDetailBO roleGroupParamDetailBO, Pageable pageable);

	void changeParent(String parentId, String id) throws Exception;

	Page<RoleGroupBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception;

	RoleGroupBaseBO queryParentById(String id) throws Exception;

	void deleteByParentId(String parentId) throws Exception;
}
