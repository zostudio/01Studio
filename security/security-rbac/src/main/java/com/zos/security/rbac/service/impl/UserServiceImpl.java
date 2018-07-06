package com.zos.security.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.bo.UserConditionBO;
import com.zos.security.rbac.bo.UserRoleBO;
import com.zos.security.rbac.bo.UserRoleRelationBO;
import com.zos.security.rbac.domain.QUser;
import com.zos.security.rbac.domain.QUserRole;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.domain.UserRole;
import com.zos.security.rbac.mapper.RoleMapper;
import com.zos.security.rbac.mapper.UserMapper;
import com.zos.security.rbac.mapper.UserRoleMapper;
import com.zos.security.rbac.redis.dao.RedisCommonDAO;
import com.zos.security.rbac.repository.UserRepository;
import com.zos.security.rbac.repository.UserRoleRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
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
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	RedisCommonDAO<User.RoleCache, Long, String> userRoleCacheRedisDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private QUserRole _Q_UserRole = QUserRole.userRole;
	
	private QUser _Q_User = QUser.user;

	@Override
	@Transactional
	public UserBO create(UserBO userBO) {
		userBO.setPassword(passwordEncoder.encode(userBO.getPassword()));
		return UserMapper.INSTANCE.domainToBO(userRepository.save(UserMapper.INSTANCE.boToDomain(userBO)));
	}

	@Override
	@Transactional
	public UserBO update(Long id, UserBO userBO) {
		return UserMapper.INSTANCE.domainToBO(userRepository.save(UserMapper.INSTANCE.boToDomain(userBO)));
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public UserBO getInfo(Long id) {
		return UserMapper.INSTANCE.domainToBO(userRepository.findOne(id));
	}

	@Override
	public Page<UserBO> query(UserConditionBO userConditionBO, Pageable pageable) {
		Predicate predicate = null;
		if (!StringUtils.isBlank(userConditionBO.getUsername())) {
			predicate = _Q_User.username.like("%"+userConditionBO.getUsername()+"%");
		}
		JPAQuery<User> users = jpaQueryFactory.selectFrom(_Q_User)
				.where(predicate)
												.orderBy(new OrderSpecifier<Long>(Order.DESC, _Q_User.id))
										        .offset(pageable.getOffset())
										        .limit(pageable.getPageSize());
		QueryResults<User> result = users.fetchResults();
		Page<UserBO> pageUserBO = QueryResultConverter.convert(UserMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return pageUserBO;
	}

	@Override
	public UserBO findByUsername(String username) {
		return UserMapper.INSTANCE.domainToBO(userRepository.findByUsername(username));
	}

	@Override
	public List<UserRoleBO> addRoles(UserRoleRelationBO userRoleRelationBO) {
		List<UserRole> entities = new ArrayList<UserRole>();
		userRoleRelationBO.getRoleBOs().forEach(roleBO -> {
			UserRole userRole = new UserRole();
			userRole.setUser(UserMapper.INSTANCE.boToDomain(userRoleRelationBO.getUserBO()));
			userRole.setRole(RoleMapper.INSTANCE.boToDomain(roleBO));
			entities.add(userRole);
		});
		List<UserRole> userRoles = userRoleRepository.save(entities);
		return UserRoleMapper.INSTANCE.domainToBO(userRoles);
	}

	@Override
	public void delRoles(Long id, UserRoleRelationBO userRoleRelationBO) {
		List<Long> ids = new ArrayList<Long>();
		userRoleRelationBO.getRoleBOs().forEach(roleBO -> {
			ids.add(roleBO.getId());
		});
		jpaQueryFactory.delete(_Q_UserRole).where(_Q_UserRole.user.id.eq(id).and(_Q_UserRole.role.id.in(ids))).execute();
	}

	@Override
	@Transactional
	public Long updatePwd(Long id, UserConditionBO userConditionBO) {
		User user = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.eq(id)).fetchOne();
		if (!passwordEncoder.matches(userConditionBO.getOldPassword(), user.getPassword())) {
			throw new BadCredentialsException("原密码错误");
		}
		return jpaQueryFactory.update(_Q_User).set(_Q_User.password, passwordEncoder.encode(userConditionBO.getNewPassword())).where(_Q_User.id.eq(id)).execute();
	}

	@Override
	public UserBO findByEmail(String email) {
		return UserMapper.INSTANCE.domainToBO(userRepository.findByEmail(email));
	}

	@Override
	public UserBO findByPhone(String phone) {
		return UserMapper.INSTANCE.domainToBO(userRepository.findByPhone(phone));
	}

}
