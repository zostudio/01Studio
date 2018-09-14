package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.RoleGroupParamBaseBO;
import com.zos.security.rbac.bo.param.detail.RoleGroupParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleGroupDetailBO;
import com.zos.security.rbac.domain.QRoleGroup;
import com.zos.security.rbac.domain.RoleGroup;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.RoleGroupBaseMapper;
import com.zos.security.rbac.mapper.detail.RoleGroupDetailMapper;
import com.zos.security.rbac.repository.RoleGroupRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleGroupService;
import com.zos.security.rbac.support.enums.RoleType;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleGroupServiceImpl implements RoleGroupService {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	private RoleGroupRepository roleGroupRepository;

	@Autowired
	private QueryDslTools queryDslTools;

	@Override
	public RoleGroupBaseBO create(RoleGroupBaseBO roleGroupBaseBO) {
		return RoleGroupBaseMapper.INSTANCE.domainToBO(roleGroupRepository.save(RoleGroupBaseMapper.INSTANCE.boToDomain(roleGroupBaseBO)));
	}

	@Override
	public RoleGroupBaseBO update(String id, RoleGroupBaseBO roleGroupBaseBO) {
		QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
		Long count = jpaQueryFactory.select(_Q_RoleGroup.id.count()).where(_Q_RoleGroup.id.eq(id)).fetchOne();
		if (ConstantValidator.isValueless(count)) {
			throw new NotExistException("角色组数据不存在");
		}
		jpaQueryFactory.update(_Q_RoleGroup).where(_Q_RoleGroup.id.eq(id))
				.set(_Q_RoleGroup.name, roleGroupBaseBO.getName())
				.set(_Q_RoleGroup.type, roleGroupBaseBO.getType())
				.set(_Q_RoleGroup.description, roleGroupBaseBO.getDescription())
				.execute();
		roleGroupBaseBO.setId(id);
		return roleGroupBaseBO;
	}

	@Override
	public void delete(String id) {
		roleGroupRepository.delete(id);
	}

	@Override
	public RoleGroupDetailBO getInfo(String id) {
		QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
		RoleGroup roleGroup = jpaQueryFactory.selectFrom(_Q_RoleGroup).where(_Q_RoleGroup.id.eq(id)).fetchOne();;
		return RoleGroupDetailMapper.INSTANCE.domainToBO(roleGroup);
	}

	@Override
	public Page<RoleGroupBaseBO> querySimple(RoleGroupParamBaseBO roleGroupParamBaseBO, Pageable pageable) {
		QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
		JPAQuery<RoleGroup> roleGroupJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_RoleGroup)).from(_Q_RoleGroup);
		Predicate predicate = _Q_RoleGroup.isNotNull();
		predicate = addBaseWhere(predicate, roleGroupParamBaseBO, _Q_RoleGroup, roleGroupJPAQuery);
		roleGroupJPAQuery.where(predicate);
		orderBy(pageable, _Q_RoleGroup, roleGroupJPAQuery);
		queryDslTools.addPageable(pageable, roleGroupJPAQuery);
		QueryResults<RoleGroup> result = roleGroupJPAQuery.fetchResults();
		Page<RoleGroupBaseBO> roleGroupSimpleBOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleGroupSimpleBOPage;
	}

	@Override
	public Page<RoleGroupDetailBO> queryDetail(RoleGroupParamDetailBO roleGroupParamDetailBO, Pageable pageable) {
		QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
		JPAQuery<RoleGroup> roleGroupJPAQuery = jpaQueryFactory.selectFrom(_Q_RoleGroup);
		Predicate predicate = _Q_RoleGroup.isNotNull();
		predicate = addDetailWhere(predicate, roleGroupParamDetailBO, _Q_RoleGroup, roleGroupJPAQuery);
		roleGroupJPAQuery.where(predicate);
		orderBy(pageable, _Q_RoleGroup, roleGroupJPAQuery);
		queryDslTools.addPageable(pageable, roleGroupJPAQuery);
		QueryResults<RoleGroup> result = roleGroupJPAQuery.fetchResults();
		Page<RoleGroupDetailBO> roleGroupDetailBOPage = QueryResultConverter.convert(RoleGroupDetailMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleGroupDetailBOPage;
	}

	@Override
	public void changeParent(String parentId, String id) throws Exception {
		RoleGroup parentRoleGroup = roleGroupRepository.findOne(parentId);
		if (ConstantValidator.isNull(parentRoleGroup)) {
			throw new NotExistException("父角色组数据不存在");
		}
		RoleGroup childRoleGroup = roleGroupRepository.findOne(id);
		if (ConstantValidator.isNull(childRoleGroup)) {
			throw new NotExistException("子角色组数据不存在");
		}
		childRoleGroup.setParent(parentRoleGroup);
		roleGroupRepository.save(childRoleGroup);
	}

	@Override
	public Page<RoleGroupBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception {
		QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
		JPAQuery<RoleGroup> roleGroupJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_RoleGroup)).from(_Q_RoleGroup);
		Predicate predicate = _Q_RoleGroup.parent.id.eq(parentId);
		roleGroupJPAQuery.where(predicate);
		orderBy(pageable, _Q_RoleGroup, roleGroupJPAQuery);
		queryDslTools.addPageable(pageable, roleGroupJPAQuery);
		QueryResults<RoleGroup> result = roleGroupJPAQuery.fetchResults();
		Page<RoleGroupBaseBO> roleGroupSimpleBOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleGroupSimpleBOPage;
	}

	@Override
	public RoleGroupBaseBO queryParentById(String id) throws Exception {
		RoleGroup childRoleGroup = roleGroupRepository.findOne(id);
		if (ConstantValidator.isNull(childRoleGroup)) {
			throw new NotExistException("子角色组数据不存在");
		}
		if (childRoleGroup.getParent() == null) {
			throw new NotExistException("父角色组数据不存在");
		}
		return RoleGroupBaseMapper.INSTANCE.domainToBO(childRoleGroup.getParent());
	}

	@Override
	public void deleteByParentId(String parentId) throws Exception {
		RoleGroup parentRoleGroup = roleGroupRepository.findOne(parentId);
		if (ConstantValidator.isNull(parentRoleGroup)) {
			throw new NotExistException("父角色组数据不存在");
		}
		if (CollectionUtils.isNotEmpty(parentRoleGroup.getChildren())) {
			roleGroupRepository.deleteInBatch(parentRoleGroup.getChildren());
		}
	}

	private QBean<RoleGroup> getBaseBean(QRoleGroup _Q_RoleGroup) {
		return Projections.bean(
				RoleGroup.class,
				_Q_RoleGroup.id,
				_Q_RoleGroup.name,
				_Q_RoleGroup.type,
				_Q_RoleGroup.description);
	}

	private Predicate addBaseWhere(Predicate predicate, RoleGroupParamBaseBO roleGroupParamBaseBO, QRoleGroup _Q_RoleGroup, JPAQuery<RoleGroup> roleGroupJPAQuery) {
		if (StringUtils.isNotEmpty(roleGroupParamBaseBO.getName())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.name.like("%" + roleGroupParamBaseBO.getName() + "%"));
		}
		if (ConstantValidator.isNotNull(roleGroupParamBaseBO.getType())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.type.eq(roleGroupParamBaseBO.getType()));
		}
		if (StringUtils.isNotEmpty(roleGroupParamBaseBO.getDescription())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.description.like("%" + roleGroupParamBaseBO.getDescription() + "%"));
		}
		return predicate;
	}

	private Predicate addDetailWhere(Predicate predicate, RoleGroupParamDetailBO roleGroupParamDetailBO, QRoleGroup _Q_RoleGroup, JPAQuery<RoleGroup> roleGroupJPAQuery) {
		predicate = addBaseWhere(predicate, roleGroupParamDetailBO, _Q_RoleGroup, roleGroupJPAQuery);
		if (ConstantValidator.isNotNull(roleGroupParamDetailBO.getCreatedDateStart())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.createdDate.goe(roleGroupParamDetailBO.getCreatedDateStart()));
		}
		if (ConstantValidator.isNotNull(roleGroupParamDetailBO.getCreatedDateEnd())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.createdDate.loe(roleGroupParamDetailBO.getCreatedDateEnd()));
		}
		if (ConstantValidator.isNotNull(roleGroupParamDetailBO.getLastModifiedDateStart())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.lastModifiedDate.goe(roleGroupParamDetailBO.getLastModifiedDateStart()));
		}
		if (ConstantValidator.isNotNull(roleGroupParamDetailBO.getLastModifiedDateEnd())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.lastModifiedDate.loe(roleGroupParamDetailBO.getLastModifiedDateEnd()));
		}
		if (ConstantValidator.isValuable(roleGroupParamDetailBO.getCreatedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.createdBy.eq(roleGroupParamDetailBO.getCreatedBy()));
		}
		if (ConstantValidator.isValuable(roleGroupParamDetailBO.getLastModifiedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_RoleGroup.lastModifiedBy.eq(roleGroupParamDetailBO.getLastModifiedBy()));
		}
		return predicate;
	}

	private void orderBy(Pageable pageable, QRoleGroup _Q_RoleGroup, JPAQuery<RoleGroup> roleGroupJPAQuery) {
		if (ConstantValidator.isNotNull(pageable.getSort())) {
			pageable.getSort().forEach(order -> {
				if (ConstantValidator.isNotNull(order) && StringUtils.isNotEmpty(order.getProperty())) {
					switch(order.getProperty()) {
						case "name":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_RoleGroup.name, queryDslTools.getNullHandling(order)));
							break;
						case "type":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<RoleType>(queryDslTools.getOrder(order), _Q_RoleGroup.type, queryDslTools.getNullHandling(order)));
							break;
						case "description":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_RoleGroup.description, queryDslTools.getNullHandling(order)));
							break;
						case "id":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_RoleGroup.id, queryDslTools.getNullHandling(order)));
							break;
						case "createdDate":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_RoleGroup.createdDate, queryDslTools.getNullHandling(order)));
							break;
						case "createdBy":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_RoleGroup.createdBy, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedDate":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_RoleGroup.lastModifiedDate, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedBy":
							roleGroupJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_RoleGroup.lastModifiedBy, queryDslTools.getNullHandling(order)));
							break;
					}
				}
			});
		}
	}
}
