package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.bo.param.detail.UserParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.bo.resopnse.info.UserInfoBO;
import com.zos.security.rbac.domain.QUser;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
import com.zos.security.rbac.mapper.detail.UserDetailMapper;
import com.zos.security.rbac.mapper.info.UserInfoMapper;
import com.zos.security.rbac.repository.UserRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.support.enums.Gender;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueryDslTools queryDslTools;

    @Override
    public UserInfoBO create(UserInfoBO userInfoBO) {
        return UserInfoMapper.INSTANCE.domainToBO(userRepository.save(UserInfoMapper.INSTANCE.boToDomain(userInfoBO)));
    }

    @Override
    public UserInfoBO update(String id, UserInfoBO userInfoBO) {
        QUser _Q_User = QUser.user;
        Long count = jpaQueryFactory.select(_Q_User.id.count()).where(_Q_User.id.eq(id)).fetchOne();
        if (ConstantValidator.isValueless(count)) {
            throw new NotExistException("账号数据不存在");
        }
        jpaQueryFactory.update(_Q_User).where(_Q_User.id.eq(id))
                .set(_Q_User.nickName, userInfoBO.getNickName())
                .set(_Q_User.phone, userInfoBO.getPhone())
                .set(_Q_User.email, userInfoBO.getEmail())
                .set(_Q_User.avatar, userInfoBO.getAvatar())
                .set(_Q_User.identity, userInfoBO.getIdentity())
                .set(_Q_User.gender, userInfoBO.getGender())
                .set(_Q_User.address, userInfoBO.getAddress())
                .set(_Q_User.dateOfBirth, userInfoBO.getDateOfBirth())
                .execute();
        userInfoBO.setId(id);
        return userInfoBO;
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public UserDetailBO getInfo(String id) {
        QUser _Q_User = QUser.user;
        User user = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.eq(id)).fetchOne();
        return UserDetailMapper.INSTANCE.domainToBO(user);
    }

    @Override
    public Page<UserBaseBO> querySimple(UserParamBaseBO userParamBaseBO, Pageable pageable) {
        QUser _Q_User = QUser.user;
        JPAQuery<User> userJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_User)).from(_Q_User);
        Predicate predicate = _Q_User.isNotNull();
        predicate = addBaseWhere(predicate, userParamBaseBO, _Q_User, userJPAQuery);
        userJPAQuery.where(predicate);
        orderBy(pageable, _Q_User, userJPAQuery);
        queryDslTools.addPageable(pageable, userJPAQuery);
        QueryResults<User> result = userJPAQuery.fetchResults();
        Page<UserBaseBO> userSimpleBOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return userSimpleBOPage;
    }

    @Override
    public Page<UserDetailBO> queryDetail(UserParamDetailBO userParamDetailBO, Pageable pageable) {
        QUser _Q_User = QUser.user;
        JPAQuery<User> userJPAQuery = jpaQueryFactory.selectFrom(_Q_User);
        Predicate predicate = _Q_User.isNotNull();
        predicate = addDetailWhere(predicate, userParamDetailBO, _Q_User, userJPAQuery);
        userJPAQuery.where(predicate);
        orderBy(pageable, _Q_User, userJPAQuery);
        queryDslTools.addPageable(pageable, userJPAQuery);
        QueryResults<User> result = userJPAQuery.fetchResults();
        Page<UserDetailBO> userDetailBOPage = QueryResultConverter.convert(UserDetailMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return userDetailBOPage;
    }

    @Override
    public Boolean existByUsername(String username) {
        QUser _Q_User = QUser.user;
        return ConstantValidator.isValueless(jpaQueryFactory.select(_Q_User.id.count()).where(_Q_User.username.eq(username)).fetchOne());
    }

    @Override
    public Boolean existByEmail(String email) {
        QUser _Q_User = QUser.user;
        return ConstantValidator.isValueless(jpaQueryFactory.select(_Q_User.id.count()).where(_Q_User.email.eq(email)).fetchOne());
    }

    @Override
    public Boolean existByPhone(String phone) {
        QUser _Q_User = QUser.user;
        return ConstantValidator.isValueless(jpaQueryFactory.select(_Q_User.id.count()).where(_Q_User.phone.eq(phone)).fetchOne());
    }

    @Override
    public User signIn(String username) {
        QUser _Q_User = QUser.user;
        return jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.username.eq(username)).fetchOne();
    }

    private QBean<User> getBaseBean(QUser _Q_User) {
        return Projections.bean(User.class, _Q_User.id, _Q_User.username, _Q_User.nickName, _Q_User.avatar, _Q_User.phone, _Q_User.email);
    }

    private Predicate addBaseWhere(Predicate predicate, UserParamBaseBO userParamBaseBO, QUser _Q_User, JPAQuery<User> userJPAQuery) {
        if (StringUtils.isNotEmpty(userParamBaseBO.getUsername())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.username.like("%" + userParamBaseBO.getUsername() + "%"));
        }
        if (StringUtils.isNotEmpty(userParamBaseBO.getNickName())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.nickName.like("%" + userParamBaseBO.getNickName() + "%"));
        }
        if (StringUtils.isNotEmpty(userParamBaseBO.getPhone())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.phone.like("%" + userParamBaseBO.getPhone() + "%"));
        }
        if (StringUtils.isNotEmpty(userParamBaseBO.getEmail())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.email.like("%" + userParamBaseBO.getEmail() + "%"));
        }
        return predicate;
    }

    private Predicate addDetailWhere(Predicate predicate, UserParamDetailBO userParamDetailBO, QUser _Q_User, JPAQuery<User> userJPAQuery) {
        predicate = addBaseWhere(predicate, userParamDetailBO, _Q_User, userJPAQuery);
        if (StringUtils.isNotEmpty(userParamDetailBO.getAddress())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.address.like("%" + userParamDetailBO.getAddress() + "%"));
        }
        if (StringUtils.isNotEmpty(userParamDetailBO.getDateOfBirth())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.dateOfBirth.like("%" + userParamDetailBO.getDateOfBirth() + "%"));
        }
        if (StringUtils.isNotEmpty(userParamDetailBO.getIdentity())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.identity.like("%" + userParamDetailBO.getIdentity() + "%"));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getGender())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.gender.eq(userParamDetailBO.getGender()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getAccountNonExpired())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.accountNonExpired.eq(userParamDetailBO.getAccountNonExpired()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getAccountNonLocked())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.accountNonLocked.eq(userParamDetailBO.getAccountNonLocked()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getCredentialsNonExpired())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.credentialsNonExpired.eq(userParamDetailBO.getCredentialsNonExpired()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getEnabled())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.enabled.eq(userParamDetailBO.getEnabled()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getCreatedDateStart())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.createdDate.goe(userParamDetailBO.getCreatedDateStart()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getCreatedDateEnd())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.createdDate.loe(userParamDetailBO.getCreatedDateEnd()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getLastModifiedDateStart())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.lastModifiedDate.goe(userParamDetailBO.getLastModifiedDateStart()));
        }
        if (ConstantValidator.isNotNull(userParamDetailBO.getLastModifiedDateEnd())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.lastModifiedDate.loe(userParamDetailBO.getLastModifiedDateEnd()));
        }
        if (ConstantValidator.isValuable(userParamDetailBO.getCreatedBy())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.createdBy.eq(userParamDetailBO.getCreatedBy()));
        }
        if (ConstantValidator.isValuable(userParamDetailBO.getLastModifiedBy())) {
            predicate = ExpressionUtils.and(predicate, _Q_User.lastModifiedBy.eq(userParamDetailBO.getLastModifiedBy()));
        }
        return predicate;
    }

    private void orderBy(Pageable pageable, QUser _Q_User, JPAQuery<User> userJPAQuery) {
        if (pageable.getSort() != null) {
            pageable.getSort().forEach(order -> {
                if (order != null && StringUtils.isNotEmpty(order.getProperty())) {
                    switch(order.getProperty()) {
                        case "username":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.username, queryDslTools.getNullHandling(order)));
                            break;
                        case "nickName":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.nickName, queryDslTools.getNullHandling(order)));
                            break;
                        case "phone":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.phone, queryDslTools.getNullHandling(order)));
                            break;
                        case "email":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.email, queryDslTools.getNullHandling(order)));
                            break;
                        case "identity":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.identity, queryDslTools.getNullHandling(order)));
                            break;
                        case "address":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.address, queryDslTools.getNullHandling(order)));
                            break;
                        case "dateOfBirth":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.dateOfBirth, queryDslTools.getNullHandling(order)));
                            break;
                        case "gender":
                            userJPAQuery.orderBy(new OrderSpecifier<Gender>(queryDslTools.getOrder(order), _Q_User.gender, queryDslTools.getNullHandling(order)));
                            break;
                        case "accountNonExpired":
                            userJPAQuery.orderBy(new OrderSpecifier<Boolean>(queryDslTools.getOrder(order), _Q_User.accountNonExpired, queryDslTools.getNullHandling(order)));
                            break;
                        case "accountNonLocked":
                            userJPAQuery.orderBy(new OrderSpecifier<Boolean>(queryDslTools.getOrder(order), _Q_User.accountNonLocked, queryDslTools.getNullHandling(order)));
                            break;
                        case "credentialsNonExpired":
                            userJPAQuery.orderBy(new OrderSpecifier<Boolean>(queryDslTools.getOrder(order), _Q_User.credentialsNonExpired, queryDslTools.getNullHandling(order)));
                            break;
                        case "enabled":
                            userJPAQuery.orderBy(new OrderSpecifier<Boolean>(queryDslTools.getOrder(order), _Q_User.enabled, queryDslTools.getNullHandling(order)));
                            break;
                        case "id":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.id, queryDslTools.getNullHandling(order)));
                            break;
                        case "createdDate":
                            userJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_User.createdDate, queryDslTools.getNullHandling(order)));
                            break;
                        case "createdBy":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.createdBy, queryDslTools.getNullHandling(order)));
                            break;
                        case "lastModifiedDate":
                            userJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_User.lastModifiedDate, queryDslTools.getNullHandling(order)));
                            break;
                        case "lastModifiedBy":
                            userJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_User.lastModifiedBy, queryDslTools.getNullHandling(order)));
                            break;
                    }
                }
            });
        }
    }

}
