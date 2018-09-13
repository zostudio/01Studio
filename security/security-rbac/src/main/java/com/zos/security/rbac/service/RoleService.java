package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.base.RoleParamBaseBO;
import com.zos.security.rbac.bo.param.detail.RoleParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

	RoleBaseBO create(RoleBaseBO roleBaseBO);

	RoleBaseBO update(String id, RoleBaseBO roleBaseBO);

	void delete(String id);

	RoleDetailBO getInfo(String id);

	Page<RoleBaseBO> querySimple(RoleParamBaseBO roleParamBaseBO, Pageable pageable);

	Page<RoleDetailBO> queryDetail(RoleParamDetailBO roleParamDetailBO, Pageable pageable);
}
