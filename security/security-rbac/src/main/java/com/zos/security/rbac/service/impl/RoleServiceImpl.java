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
import com.zos.security.rbac.bo.RoleBO;
import com.zos.security.rbac.bo.RoleConditionBO;
import com.zos.security.rbac.domain.QRole;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.mapper.RoleMapper;
import com.zos.security.rbac.repository.RoleRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
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
	private RoleRepository roleRepository;

	@Override
	public RoleBO create(RoleBO roleBO) {
		return RoleMapper.INSTANCE.domainToBO(roleRepository.save(RoleMapper.INSTANCE.boToDomain(roleBO)));
	}

	@Override
	public RoleBO update(RoleBO roleBO) {
		return RoleMapper.INSTANCE.domainToBO(roleRepository.save(RoleMapper.INSTANCE.boToDomain(roleBO)));
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public RoleBO getInfo(Long id) {
		return RoleMapper.INSTANCE.domainToBO(roleRepository.findOne(id));
	}

	@Override
	public Page<RoleBO> query(RoleConditionBO roleConditionBO, Pageable pageable) {
		QRole _Q_Role = QRole.role;
		JPAQuery<Role> roles = jpaQueryFactory.selectFrom(_Q_Role)
        .orderBy(new OrderSpecifier<Long>(Order.DESC, _Q_Role.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());
		QueryResults<Role> result = roles.fetchResults();
		Page<RoleBO> pageRoleBO = QueryResultConverter.convert(RoleMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return pageRoleBO;
	}

}
