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
import com.zos.security.rbac.bo.ResourceBO;
import com.zos.security.rbac.bo.ResourceConditionBO;
import com.zos.security.rbac.domain.QResource;
import com.zos.security.rbac.domain.Resource;
import com.zos.security.rbac.mapper.ResourceMapper;
import com.zos.security.rbac.repository.ResourceRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {
	
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
	private ResourceRepository resourceRepository;

	@Override
	public ResourceBO create(ResourceBO resourceBO) {
		return ResourceMapper.INSTANCE.domainToBO(resourceRepository.save(ResourceMapper.INSTANCE.boToDomain(resourceBO)));
	}

	@Override
	public ResourceBO update(ResourceBO resourceBO) {
		return ResourceMapper.INSTANCE.domainToBO(resourceRepository.save(ResourceMapper.INSTANCE.boToDomain(resourceBO)));
	}

	@Override
	public void delete(Long id) {
		resourceRepository.delete(id);
	}

	@Override
	public ResourceBO getInfo(Long id) {
		return ResourceMapper.INSTANCE.domainToBO(resourceRepository.findOne(id));
	}

	@Override
	public Page<ResourceBO> query(ResourceConditionBO ResourceConditionBO, Pageable pageable) {
		QResource _Q_Resource = QResource.resource;
		JPAQuery<Resource> Resources = jpaQueryFactory.selectFrom(_Q_Resource)
        .orderBy(new OrderSpecifier<Long>(Order.DESC, _Q_Resource.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());
		QueryResults<Resource> result = Resources.fetchResults();
		Page<ResourceBO> pageResourceBO = QueryResultConverter.convert(ResourceMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return pageResourceBO;
	}
}
