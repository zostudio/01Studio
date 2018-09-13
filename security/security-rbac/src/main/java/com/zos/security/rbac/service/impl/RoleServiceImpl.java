package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.bo.RoleConditionBO;
import com.zos.security.rbac.domain.QRole;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.mapper.RoleMapper;
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

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * JPA查询工厂
     */
	@Autowired
    private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
    private QueryDslTools queryDslTools;

	@Override
	public RoleBO create(RoleBO roleBO) {
		return RoleMapper.INSTANCE.domainToBO(roleRepository.save(RoleMapper.INSTANCE.boToDomain(roleBO)));
	}

	@Override
	public RoleBO update(String id, RoleBO roleBO) {
		Role role = roleRepository.findOne(id);
		role.setName(roleBO.getName());
		role.setType(roleBO.getType());
		role.setDescription(roleBO.getDescription());
		return RoleMapper.INSTANCE.domainToBO(roleRepository.save(role));
	}

	@Override
	public void delete(String id) {
		roleRepository.delete(id);
	}

	@Override
	public RoleBO getInfo(String id) {
		return RoleMapper.INSTANCE.domainToBO(roleRepository.findOne(id));
	}

	@Override
	public Page<RoleBO> query(RoleConditionBO roleConditionBO, Pageable pageable) {
		QRole _Q_Role = QRole.role;
		JPAQuery<Role> roles = jpaQueryFactory.selectFrom(_Q_Role);
		Predicate predicate = null;
		if (StringUtils.isNotEmpty(roleConditionBO.getName())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.name.like("%" + roleConditionBO.getName() + "%"));
		}
		if (StringUtils.isNotEmpty(roleConditionBO.getDescription())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.description.like("%" + roleConditionBO.getDescription() + "%"));
		}
		if (ConstantValidator.isAvaluableId(roleConditionBO.getId())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.id.eq(roleConditionBO.getId()));
		}
		if (ConstantValidator.isNotNull(roleConditionBO.getRoleType())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.type.eq(roleConditionBO.getRoleType()));
		}
		if (predicate != null) {
			roles.where(predicate);
		}
		if (pageable.getSort() != null) {
			pageable.getSort().forEach(order -> {
				if (order != null && StringUtils.isNotEmpty(order.getProperty())) {
					switch(order.getProperty()) {
						case "id" :
							roles.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.id, queryDslTools.getNullHandling(order)));
							break;
						case "name":
							roles.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.name, queryDslTools.getNullHandling(order)));
							break;
						case "orderType":
							roles.orderBy(new OrderSpecifier<RoleType>(queryDslTools.getOrder(order), _Q_Role.type, queryDslTools.getNullHandling(order)));
							break;
						case "description":
							roles.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.description, queryDslTools.getNullHandling(order)));
							break;
					}
				}
			});
		}
		if (pageable.getOffset() >= 0 && pageable.getPageSize() > 0) {
			roles.offset(pageable.getOffset()).limit(pageable.getPageSize());
		}
		QueryResults<Role> result = roles.fetchResults();
		Page<RoleBO> pageRoleBO = QueryResultConverter.convert(RoleMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return pageRoleBO;
	}



}
