package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.ResourceParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.ResourceParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.ResourceBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.ResourceDetailBO;
import com.zos.security.rbac.domain.*;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.ResourceBaseMapper;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.detail.ResourceDetailMapper;
import com.zos.security.rbac.repository.ResourceRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.ResourceService;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.support.enums.RequestMethod;
import com.zos.security.rbac.support.enums.ResourceType;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	private ResourceRepository resourceRepository;

	@Autowired
	private QueryDslTools queryDslTools;

	@Autowired
	private EntityManager entityManager;

	@Autowired
    private RoleService roleService;

	@Override
	public ResourceBaseBO create(ResourceBaseBO resourceBaseBO) {
		return ResourceBaseMapper.INSTANCE.domainToBO(resourceRepository.save(ResourceBaseMapper.INSTANCE.boToDomain(resourceBaseBO)));
	}

	@Override
	public ResourceBaseBO update(String id, ResourceBaseBO resourceBaseBO) {
		QResource _Q_Resource = QResource.resource;
		Long count = jpaQueryFactory.select(_Q_Resource.id.count()).where(_Q_Resource.id.eq(id)).fetchOne();
		if (ConstantValidator.isValueless(count)) {
			throw new NotExistException("资源数据不存在");
		}
		jpaQueryFactory.update(_Q_Resource).where(_Q_Resource.id.eq(id))
				.set(_Q_Resource.name, resourceBaseBO.getName())
				.set(_Q_Resource.url, resourceBaseBO.getUrl())
                .set(_Q_Resource.icon, resourceBaseBO.getIcon())
                .set(_Q_Resource.type, resourceBaseBO.getType())
                .set(_Q_Resource.method, resourceBaseBO.getMethod())
				.set(_Q_Resource.description, resourceBaseBO.getDescription())
				.execute();
		resourceBaseBO.setId(id);
		return resourceBaseBO;
	}

	@Override
	public void delete(String id) {
		resourceRepository.delete(id);
	}

	@Override
	public ResourceDetailBO getInfo(String id) {
        QResource _Q_Resource = QResource.resource;
        Resource resource = jpaQueryFactory.selectFrom(_Q_Resource).where(_Q_Resource.id.eq(id)).fetchOne();;
        return ResourceDetailMapper.INSTANCE.domainToBO(resource);
	}

	@Override
	public Page<ResourceBaseBO> queryBase(ResourceParamBaseBO resourceParamBaseBO, Pageable pageable) {
		QResource _Q_Resource = QResource.resource;
		JPAQuery<Resource> resourceJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Resource)).from(_Q_Resource);
		Predicate predicate = _Q_Resource.isNotNull();
		predicate = addBaseWhere(predicate, resourceParamBaseBO, _Q_Resource, resourceJPAQuery);
		resourceJPAQuery.where(predicate);
		orderBy(pageable, _Q_Resource, resourceJPAQuery);
		queryDslTools.addPageable(pageable, resourceJPAQuery);
		QueryResults<Resource> result = resourceJPAQuery.fetchResults();
		Page<ResourceBaseBO> resourceBaseBOPage = QueryResultConverter.convert(ResourceBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return resourceBaseBOPage;
	}

    @Override
	public Page<ResourceDetailBO> queryDetail(ResourceParamDetailBO resourceParamDetailBO, Pageable pageable) {
		QResource _Q_Resource = QResource.resource;
		JPAQuery<Resource> resourceJPAQuery = jpaQueryFactory.selectFrom(_Q_Resource);
		Predicate predicate = _Q_Resource.isNotNull();
		predicate = addDetailWhere(predicate, resourceParamDetailBO, _Q_Resource, resourceJPAQuery);
		resourceJPAQuery.where(predicate);
		orderBy(pageable, _Q_Resource, resourceJPAQuery);
		queryDslTools.addPageable(pageable, resourceJPAQuery);
		QueryResults<Resource> result = resourceJPAQuery.fetchResults();
		Page<ResourceDetailBO> resourceDetailBOPage = QueryResultConverter.convert(ResourceDetailMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return resourceDetailBOPage;
	}

	@Override
	public void changeParent(String parentId, String id) throws Exception {
		Resource parentResource = resourceRepository.findOne(parentId);
		if (ConstantValidator.isNull(parentResource)) {
			throw new NotExistException("父资源数据不存在");
		}
		Resource childResource = resourceRepository.findOne(id);
		if (ConstantValidator.isNull(childResource)) {
			throw new NotExistException("子资源数据不存在");
		}
		childResource.setParent(parentResource);
		resourceRepository.save(childResource);
	}

	@Override
	public Page<ResourceBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception {
		QResource _Q_Resource = QResource.resource;
		JPAQuery<Resource> resourceJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Resource)).from(_Q_Resource);
		Predicate predicate = _Q_Resource.parent.id.eq(parentId);
		resourceJPAQuery.where(predicate);
		orderBy(pageable, _Q_Resource, resourceJPAQuery);
		queryDslTools.addPageable(pageable, resourceJPAQuery);
		QueryResults<Resource> result = resourceJPAQuery.fetchResults();
		Page<ResourceBaseBO> resourceBaseBOPage = QueryResultConverter.convert(ResourceBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return resourceBaseBOPage;
	}

	@Override
	public ResourceBaseBO queryParentById(String id) throws Exception {
		Resource childResource = resourceRepository.findOne(id);
		if (ConstantValidator.isNull(childResource)) {
			throw new NotExistException("子资源数据不存在");
		}
		if (childResource.getParent() == null) {
			throw new NotExistException("父资源数据不存在");
		}
		return ResourceBaseMapper.INSTANCE.domainToBO(childResource.getParent());
	}

	@Override
	public void deleteByParentId(String parentId) throws Exception {
		Resource parentResource = resourceRepository.findOne(parentId);
		if (ConstantValidator.isNull(parentResource)) {
			throw new NotExistException("父资源数据不存在");
		}
		if (CollectionUtils.isNotEmpty(parentResource.getChildren())) {
			resourceRepository.deleteInBatch(parentResource.getChildren());
		}
	}

	@Override
	public Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception {
		QResource _Q_Resource = QResource.resource;
		QRole _Q_Role = QRole.role;
		QRoleResourceRelation _Q_RoleResourceRelation = QRoleResourceRelation.roleResourceRelation;
		JPAQuery<Role> roleJPAQuery = jpaQueryFactory.select(roleService.getBaseBean(_Q_Role)).from(_Q_Role);
		roleJPAQuery.leftJoin(_Q_Role.roleResourceRelations, _Q_RoleResourceRelation).leftJoin(_Q_RoleResourceRelation.resource, _Q_Resource);
		Predicate predicate = _Q_Resource.id.eq(id);
		roleJPAQuery.where(predicate);
		roleService.orderBy(pageable, _Q_Role, roleJPAQuery);
		queryDslTools.addPageable(pageable, roleJPAQuery);
		QueryResults<Role> result = roleJPAQuery.fetchResults();
		Page<RoleBaseBO> roleBaseBOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleBaseBOPage;
	}

	@Override
	public Long batchAddRole(String id, RelationsBO relationsBO) throws Exception {
        QResource _Q_Resource = QResource.resource;
        QRole _Q_Role = QRole.role;
        Resource resource = jpaQueryFactory.selectFrom(_Q_Resource).where(_Q_Resource.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(resource)) {
            throw new NotExistException("资源数据不存在");
        }
        List<Role> roles = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
                roleResourceRelation.setResource(resource);
                roleResourceRelation.setRole(role);
                entityManager.persist(roleResourceRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(roles) ? roles.size() : 0);
	}

	@Override
	public void batchRemoveRole(String id, RelationsBO relationsBO) throws Exception {
        QRoleResourceRelation _Q_RoleResourceRelation = QRoleResourceRelation.roleResourceRelation;
        JPADeleteClause teamRoleRelationJPAQuery = jpaQueryFactory.delete(_Q_RoleResourceRelation);
        Predicate predicate = _Q_RoleResourceRelation.resource.id.eq(id).and(_Q_RoleResourceRelation.role.id.in(relationsBO.getRelationsIds()));
        teamRoleRelationJPAQuery.where(predicate);
        teamRoleRelationJPAQuery.execute();
	}

	@Override
	public QBean<Resource> getBaseBean(QResource _Q_Resource) {
        return Projections.bean(
                Resource.class,
                _Q_Resource.id,
                _Q_Resource.name,
                _Q_Resource.url,
                _Q_Resource.icon,
                _Q_Resource.type,
                _Q_Resource.method,
                _Q_Resource.description);
    }

	@Override
	public void orderBy(Pageable pageable, QResource _Q_Resource, JPAQuery<Resource> resourceJPAQuery) {
		if (ConstantValidator.isNotNull(pageable.getSort())) {
			pageable.getSort().forEach(order -> {
				if (ConstantValidator.isNotNull(order) && StringUtils.isNotEmpty(order.getProperty())) {
					switch(order.getProperty()) {
						case "name":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.name, queryDslTools.getNullHandling(order)));
							break;
						case "url":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.url, queryDslTools.getNullHandling(order)));
							break;
						case "icon":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.icon, queryDslTools.getNullHandling(order)));
							break;
						case "type":
							resourceJPAQuery.orderBy(new OrderSpecifier<ResourceType>(queryDslTools.getOrder(order), _Q_Resource.type, queryDslTools.getNullHandling(order)));
							break;
						case "method":
							resourceJPAQuery.orderBy(new OrderSpecifier<RequestMethod>(queryDslTools.getOrder(order), _Q_Resource.method, queryDslTools.getNullHandling(order)));
							break;
						case "description":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.description, queryDslTools.getNullHandling(order)));
							break;
						case "id":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.id, queryDslTools.getNullHandling(order)));
							break;
						case "createdDate":
							resourceJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Resource.createdDate, queryDslTools.getNullHandling(order)));
							break;
						case "createdBy":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.createdBy, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedDate":
							resourceJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Resource.lastModifiedDate, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedBy":
							resourceJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Resource.lastModifiedBy, queryDslTools.getNullHandling(order)));
							break;
					}
				}
			});
		}
	}

	private Predicate addBaseWhere(Predicate predicate, ResourceParamBaseBO resourceParamBaseBO, QResource _Q_Resource, JPAQuery<Resource> resourceJPAQuery) {
		if (StringUtils.isNotEmpty(resourceParamBaseBO.getName())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.name.like("%" + resourceParamBaseBO.getName() + "%"));
		}
		if (StringUtils.isNotEmpty(resourceParamBaseBO.getUrl())) {
            predicate = ExpressionUtils.and(predicate, _Q_Resource.url.like("%" + resourceParamBaseBO.getUrl() + "%"));
        }
        if (StringUtils.isNotEmpty(resourceParamBaseBO.getIcon())) {
            predicate = ExpressionUtils.and(predicate, _Q_Resource.icon.like("%" + resourceParamBaseBO.getIcon() + "%"));
        }
        if (ConstantValidator.isNotNull(resourceParamBaseBO.getType())) {
            predicate = ExpressionUtils.and(predicate, _Q_Resource.type.eq(resourceParamBaseBO.getType()));
        }
        if (ConstantValidator.isNotNull(resourceParamBaseBO.getMethod())) {
            predicate = ExpressionUtils.and(predicate, _Q_Resource.method.eq(resourceParamBaseBO.getMethod()));
        }
		if (StringUtils.isNotEmpty(resourceParamBaseBO.getDescription())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.description.like("%" + resourceParamBaseBO.getDescription() + "%"));
		}
		return predicate;
	}

	private Predicate addDetailWhere(Predicate predicate, ResourceParamDetailBO resourceParamDetailBO, QResource _Q_Resource, JPAQuery<Resource> resourceJPAQuery) {
		predicate = addBaseWhere(predicate, resourceParamDetailBO, _Q_Resource, resourceJPAQuery);
		if (ConstantValidator.isNotNull(resourceParamDetailBO.getCreatedDateStart())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.createdDate.goe(resourceParamDetailBO.getCreatedDateStart()));
		}
		if (ConstantValidator.isNotNull(resourceParamDetailBO.getCreatedDateEnd())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.createdDate.loe(resourceParamDetailBO.getCreatedDateEnd()));
		}
		if (ConstantValidator.isNotNull(resourceParamDetailBO.getLastModifiedDateStart())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.lastModifiedDate.goe(resourceParamDetailBO.getLastModifiedDateStart()));
		}
		if (ConstantValidator.isNotNull(resourceParamDetailBO.getLastModifiedDateEnd())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.lastModifiedDate.loe(resourceParamDetailBO.getLastModifiedDateEnd()));
		}
		if (ConstantValidator.isValuable(resourceParamDetailBO.getCreatedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.createdBy.eq(resourceParamDetailBO.getCreatedBy()));
		}
		if (ConstantValidator.isValuable(resourceParamDetailBO.getLastModifiedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_Resource.lastModifiedBy.eq(resourceParamDetailBO.getLastModifiedBy()));
		}
		return predicate;
	}
}