package com.zos.security.rbac.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserConditionBO;
import com.zos.security.rbac.mapper.UserMapper;
import com.zos.security.rbac.repository.UserRepository;
import com.zos.security.rbac.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	/**
	 * 实体管理者
	 */
    @Autowired
    private EntityManager entityManager;

    /**
     * JPA查询工厂
     */
    private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserBO create(UserBO userBO) {
		return UserMapper.INSTANCE.domainToBo(userRepository.save(UserMapper.INSTANCE.boToDomain(userBO)));
	}

	@Override
	public UserBO update(UserBO userBO) {
		return UserMapper.INSTANCE.domainToBo(userRepository.save(UserMapper.INSTANCE.boToDomain(userBO)));
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public UserBO getInfo(Long id) {
		return UserMapper.INSTANCE.domainToBo(userRepository.findOne(id));
	}

	@Override
	public Page<UserBO> query(UserConditionBO userConditionBO, Pageable pageable) {
//		userRepository.findAll(null, pageable);
//		List<T> contents = pageData.getContent();
//		List<I> infos = convert(contents, clazz);
//		return new PageImpl<I>(infos, pageable, pageData.getTotalElements());
		return null;
	}

}
