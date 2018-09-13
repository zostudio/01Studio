package com.zos.security.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.RoleGroupBO;
import com.zos.security.rbac.bo.RoleGroupConditionBO;
import com.zos.security.rbac.domain.QRoleGroup;
import com.zos.security.rbac.domain.RoleGroup;
import com.zos.security.rbac.mapper.RoleGroupMapper;
import com.zos.security.rbac.repository.RoleGroupRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleGroupService;

@Service
public class RoleGroupServiceImpl implements RoleGroupService {
	
	/**
	 * 实体管理者
	 */
//    @Autowired
//    private EntityManager entityManager;

    /**
     * JPA查询工厂
     */
	@Autowired
    private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private RoleGroupRepository roleGroupRepository;
	
	@Override
	public RoleGroupBO create(RoleGroupBO RoleGroupBO) {
		return RoleGroupMapper.INSTANCE.domainToBO(roleGroupRepository.save(RoleGroupMapper.INSTANCE.boToDomain(RoleGroupBO)));
	}

	@Override
	public RoleGroupBO update(RoleGroupBO RoleGroupBO) {
		return RoleGroupMapper.INSTANCE.domainToBO(roleGroupRepository.save(RoleGroupMapper.INSTANCE.boToDomain(RoleGroupBO)));
	}

	@Override
	public void delete(String id) {
		roleGroupRepository.delete(id);
	}

	@Override
	public RoleGroupBO getInfo(String id) {
		return RoleGroupMapper.INSTANCE.domainToBO(roleGroupRepository.findOne(id));
	}

	@Override
	public Page<RoleGroupBO> query(RoleGroupConditionBO RoleGroupConditionBO, Pageable pageable) {
		QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
		JPAQuery<RoleGroup> RoleGroups = jpaQueryFactory.selectFrom(_Q_RoleGroup)
        .orderBy(new OrderSpecifier<String>(Order.DESC, _Q_RoleGroup.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());
		QueryResults<RoleGroup> result = RoleGroups.fetchResults();
		Page<RoleGroupBO> pageRoleGroupBO = QueryResultConverter.convert(RoleGroupMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return pageRoleGroupBO;
	}

}
