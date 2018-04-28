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
import com.zos.security.rbac.bo.DepartmentBO;
import com.zos.security.rbac.bo.DepartmentConditionBO;
import com.zos.security.rbac.domain.QDepartment;
import com.zos.security.rbac.domain.Department;
import com.zos.security.rbac.mapper.DepartmentMapper;
import com.zos.security.rbac.repository.DepartmentRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
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
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentBO create(DepartmentBO departmentBO) {
		return DepartmentMapper.INSTANCE.domainToBO(departmentRepository.save(DepartmentMapper.INSTANCE.boToDomain(departmentBO)));
	}

	@Override
	public DepartmentBO update(DepartmentBO departmentBO) {
		return DepartmentMapper.INSTANCE.domainToBO(departmentRepository.save(DepartmentMapper.INSTANCE.boToDomain(departmentBO)));
	}

	@Override
	public void delete(Long id) {
		departmentRepository.delete(id);
	}

	@Override
	public DepartmentBO getInfo(Long id) {
		return DepartmentMapper.INSTANCE.domainToBO(departmentRepository.findOne(id));
	}

	@Override
	public Page<DepartmentBO> query(DepartmentConditionBO departmentConditionBO, Pageable pageable) {
		QDepartment _Q_Department = QDepartment.department;
		JPAQuery<Department> Departments = jpaQueryFactory.selectFrom(_Q_Department)
        .orderBy(new OrderSpecifier<Long>(Order.DESC, _Q_Department.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());
		QueryResults<Department> result = Departments.fetchResults();
		Page<DepartmentBO> pageDepartmentBO = QueryResultConverter.convert(DepartmentMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return pageDepartmentBO;
	}

}
