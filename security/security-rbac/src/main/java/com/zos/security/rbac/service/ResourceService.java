package com.zos.security.rbac.service;

import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.zos.security.rbac.bo.param.base.ResourceParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.ResourceParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.ResourceBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.ResourceDetailBO;
import com.zos.security.rbac.domain.QResource;
import com.zos.security.rbac.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResourceService {

	ResourceBaseBO create(ResourceBaseBO resourceBaseBO);

	ResourceBaseBO update(String id, ResourceBaseBO resourceBaseBO);

	void delete(String id);

	ResourceDetailBO getInfo(String id);

	Page<ResourceBaseBO> queryBase(ResourceParamBaseBO resourceParamBaseBO, Pageable pageable);

	Page<ResourceDetailBO> queryDetail(ResourceParamDetailBO resourceParamDetailBO, Pageable pageable);

	QBean<Resource> getBaseBean(QResource _Q_QResource);

	void orderBy(Pageable pageable, QResource _Q_QResource, JPAQuery<Resource> resourceJPAQuery);

	void changeParent(String parentId, String id) throws Exception;

	Page<ResourceBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception;

	ResourceBaseBO queryParentById(String id) throws Exception;

	void deleteByParentId(String parentId) throws Exception;

	Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception;

	Long batchAddRole(String id,  RelationsBO relationsBO) throws Exception;

	void batchRemoveRole(String id,  RelationsBO relationsBO) throws Exception;
}
