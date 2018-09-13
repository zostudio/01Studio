package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.RoleParamBaseBO;
import com.zos.security.rbac.bo.param.detail.RoleParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import com.zos.security.rbac.domain.QRole;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.detail.RoleDetailMapper;
import com.zos.security.rbac.repository.RoleRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.support.enums.RoleType;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private QueryDslTools queryDslTools;

	@Override
	public RoleBaseBO create(RoleBaseBO roleBaseBO) {
		return RoleBaseMapper.INSTANCE.domainToBO(roleRepository.save(RoleBaseMapper.INSTANCE.boToDomain(roleBaseBO)));
	}

	@Override
	public RoleBaseBO update(String id, RoleBaseBO roleBaseBO) {
		QRole _Q_Role = QRole.role;
		Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();
		if (ConstantValidator.isNull(role)) {
			throw new NotExistException("角色数据不存在");
		}
		jpaQueryFactory.update(_Q_Role).where(_Q_Role.id.eq(id))
				.set(_Q_Role.name, roleBaseBO.getName())
				.set(_Q_Role.type, roleBaseBO.getType())
				.set(_Q_Role.description, roleBaseBO.getDescription())
				.execute();
		roleBaseBO.setId(id);
		return roleBaseBO;
	}

	@Override
	public void delete(String id) {
		roleRepository.delete(id);
	}

	@Override
	public RoleDetailBO getInfo(String id) {
		QRole _Q_Role = QRole.role;
		Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();;
		return RoleDetailMapper.INSTANCE.domainToBO(role);
	}

	@Override
	public Page<RoleBaseBO> querySimple(RoleParamBaseBO roleParamBaseBO, Pageable pageable) {
		QRole _Q_Role = QRole.role;
		JPAQuery<Role> roleJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Role)).from(_Q_Role);
		Predicate predicate = _Q_Role.isNotNull();
		predicate = addBaseWhere(predicate, roleParamBaseBO, _Q_Role, roleJPAQuery);
		roleJPAQuery.where(predicate);
		orderBy(pageable, _Q_Role, roleJPAQuery);
		queryDslTools.addPageable(pageable, roleJPAQuery);
		QueryResults<Role> result = roleJPAQuery.fetchResults();
		Page<RoleBaseBO> roleSimpleBOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleSimpleBOPage;
	}

	@Override
	public Page<RoleDetailBO> queryDetail(RoleParamDetailBO roleParamDetailBO, Pageable pageable) {
		QRole _Q_Role = QRole.role;
		JPAQuery<Role> roleJPAQuery = jpaQueryFactory.selectFrom(_Q_Role);
		Predicate predicate = _Q_Role.isNotNull();
		predicate = addDetailWhere(predicate, roleParamDetailBO, _Q_Role, roleJPAQuery);
		roleJPAQuery.where(predicate);
		orderBy(pageable, _Q_Role, roleJPAQuery);
		queryDslTools.addPageable(pageable, roleJPAQuery);
		QueryResults<Role> result = roleJPAQuery.fetchResults();
		Page<RoleDetailBO> roleDetailBOPage = QueryResultConverter.convert(RoleDetailMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleDetailBOPage;
	}

	private QBean<Role> getBaseBean(QRole _Q_Role) {
		return Projections.bean(
				Role.class,
				_Q_Role.id,
				_Q_Role.name,
				_Q_Role.type,
				_Q_Role.description);
	}

	private Predicate addBaseWhere(Predicate predicate, RoleParamBaseBO roleParamBaseBO, QRole _Q_Role, JPAQuery<Role> roleJPAQuery) {
		if (StringUtils.isNotEmpty(roleParamBaseBO.getName())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.name.like("%" + roleParamBaseBO.getName() + "%"));
		}
		if (ConstantValidator.isNotNull(roleParamBaseBO.getType())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.type.eq(roleParamBaseBO.getType()));
		}
		if (StringUtils.isNotEmpty(roleParamBaseBO.getDescription())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.description.like("%" + roleParamBaseBO.getDescription() + "%"));
		}
		return predicate;
	}

	private Predicate addDetailWhere(Predicate predicate, RoleParamDetailBO roleParamDetailBO, QRole _Q_Role, JPAQuery<Role> roleJPAQuery) {
		predicate = addBaseWhere(predicate, roleParamDetailBO, _Q_Role, roleJPAQuery);
		if (roleParamDetailBO.getCreatedDateStart() != null) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.createdDate.goe(roleParamDetailBO.getCreatedDateStart()));
		}
		if (roleParamDetailBO.getCreatedDateEnd() != null) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.createdDate.loe(roleParamDetailBO.getCreatedDateEnd()));
		}
		if (roleParamDetailBO.getLastModifiedDateStart() != null) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.lastModifiedDate.goe(roleParamDetailBO.getLastModifiedDateStart()));
		}
		if (roleParamDetailBO.getLastModifiedDateEnd() != null) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.lastModifiedDate.loe(roleParamDetailBO.getLastModifiedDateEnd()));
		}
		if (ConstantValidator.isAvaluableId(roleParamDetailBO.getCreatedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.createdBy.eq(roleParamDetailBO.getCreatedBy()));
		}
		if (ConstantValidator.isAvaluableId(roleParamDetailBO.getLastModifiedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.lastModifiedBy.eq(roleParamDetailBO.getLastModifiedBy()));
		}
		return predicate;
	}

	private void orderBy(Pageable pageable, QRole _Q_Role, JPAQuery<Role> roleJPAQuery) {
		if (pageable.getSort() != null) {
			pageable.getSort().forEach(order -> {
				if (order != null && StringUtils.isNotEmpty(order.getProperty())) {
					switch(order.getProperty()) {
						case "name":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.name, queryDslTools.getNullHandling(order)));
							break;
						case "type":
							roleJPAQuery.orderBy(new OrderSpecifier<RoleType>(queryDslTools.getOrder(order), _Q_Role.type, queryDslTools.getNullHandling(order)));
							break;
						case "description":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.description, queryDslTools.getNullHandling(order)));
							break;
						case "id":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.id, queryDslTools.getNullHandling(order)));
							break;
						case "createdDate":
							roleJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Role.createdDate, queryDslTools.getNullHandling(order)));
							break;
						case "createdBy":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.createdBy, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedDate":
							roleJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Role.lastModifiedDate, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedBy":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.lastModifiedBy, queryDslTools.getNullHandling(order)));
							break;
					}
				}
			});
		}
	}
}
