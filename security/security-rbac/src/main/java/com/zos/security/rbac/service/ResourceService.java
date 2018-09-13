package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.base.ResourceParamBaseBO;
import com.zos.security.rbac.bo.param.detail.ResourceParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.ResourceBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.ResourceDetailBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResourceService {

	ResourceBaseBO create(ResourceBaseBO resourceBaseBO);

	ResourceBaseBO update(String id, ResourceBaseBO resourceBaseBO);

	void delete(String id);

	ResourceDetailBO getInfo(String id);

	Page<ResourceBaseBO> querySimple(ResourceParamBaseBO resourceParamBaseBO, Pageable pageable);

	Page<ResourceDetailBO> queryDetail(ResourceParamDetailBO resourceParamDetailBO, Pageable pageable);

	void changeParent(String parentId, String id) throws Exception;

	Page<ResourceBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception;

	ResourceBaseBO queryParentById(String id) throws Exception;

	void deleteByParentId(String parentId) throws Exception;
}
