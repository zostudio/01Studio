package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.UserParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.bo.resopnse.info.UserInfoBO;
import com.zos.security.rbac.domain.*;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.base.RoleGroupBaseMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
import com.zos.security.rbac.mapper.detail.UserDetailMapper;
import com.zos.security.rbac.mapper.info.UserInfoMapper;
import com.zos.security.rbac.repository.UserRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleGroupService;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.service.TeamService;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.support.enums.Gender;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueryDslTools queryDslTools;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleGroupService roleGroupService;

    @Autowired
    private TeamService teamService;

    @Override
    public UserInfoBO create(UserInfoBO userInfoBO) {
        userInfoBO.setPassword(passwordEncoder.encode(userInfoBO.getPassword()));
        userInfoBO = UserInfoMapper.INSTANCE.domainToBO(userRepository.save(UserInfoMapper.INSTANCE.boToDomain(userInfoBO)));
        userInfoBO.setPassword(null);
        return userInfoBO;
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
    public Page<UserBaseBO> queryBase(UserParamBaseBO userParamBaseBO, Pageable pageable) {
        QUser _Q_User = QUser.user;
        JPAQuery<User> userJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_User)).from(_Q_User);
        Predicate predicate = _Q_User.isNotNull();
        predicate = addBaseWhere(predicate, userParamBaseBO, _Q_User, userJPAQuery);
        userJPAQuery.where(predicate);
        orderBy(pageable, _Q_User, userJPAQuery);
        queryDslTools.addPageable(pageable, userJPAQuery);
        QueryResults<User> result = userJPAQuery.fetchResults();
        Page<UserBaseBO> userBaseBOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return userBaseBOPage;
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
    public QBean<User> getBaseBean(QUser _Q_User) {
        return Projections.bean(User.class, _Q_User.id, _Q_User.username, _Q_User.nickName, _Q_User.avatar, _Q_User.phone, _Q_User.email);
    }

    @Override
    public void orderBy(Pageable pageable, QUser _Q_User, JPAQuery<User> userJPAQuery) {
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

    @Override
    public Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception {
        QUser _Q_User = QUser.user;
        QRole _Q_Role = QRole.role;
        QUserRoleRelation _Q_UserRoleRelation = QUserRoleRelation.userRoleRelation;
        JPAQuery<Role> roleJPAQuery = jpaQueryFactory.select(roleService.getBaseBean(_Q_Role)).from(_Q_Role);
        roleJPAQuery.leftJoin(_Q_Role.userRoleRelations, _Q_UserRoleRelation).leftJoin(_Q_UserRoleRelation.user, _Q_User);
        Predicate predicate = _Q_User.id.eq(id);
        roleJPAQuery.where(predicate);
        roleService.orderBy(pageable, _Q_Role, roleJPAQuery);
        queryDslTools.addPageable(pageable, roleJPAQuery);
        QueryResults<Role> result = roleJPAQuery.fetchResults();
        Page<RoleBaseBO> roleBaseBOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return roleBaseBOPage;
    }

    @Override
    public Long batchAddRole(String id, RelationsBO relationsBO) throws Exception {
        QUser _Q_User = QUser.user;
        QRole _Q_Role = QRole.role;
        User user = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(user)) {
            throw new NotExistException("账号数据不存在");
        }
        List<Role> roles = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                UserRoleRelation userRoleRelation = new UserRoleRelation();
                userRoleRelation.setUser(user);
                userRoleRelation.setRole(role);
                entityManager.persist(userRoleRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(roles) ? roles.size() : 0);
    }

    @Override
    public void batchRemoveRole(String id, RelationsBO relationsBO) throws Exception {
        QUserRoleRelation _Q_UserRoleRelation = QUserRoleRelation.userRoleRelation;
        JPADeleteClause teamRoleRelationJPAQuery = jpaQueryFactory.delete(_Q_UserRoleRelation);
        Predicate predicate = _Q_UserRoleRelation.user.id.eq(id).and(_Q_UserRoleRelation.role.id.in(relationsBO.getRelationsIds()));
        teamRoleRelationJPAQuery.where(predicate);
        teamRoleRelationJPAQuery.execute();
    }

    @Override
    public Page<RoleGroupBaseBO> queryRoleGroup(String id, Pageable pageable) throws Exception {
        QUser _Q_User = QUser.user;
        QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
        QUserRoleGroupRelation _Q_UserRoleGroupRelation = QUserRoleGroupRelation.userRoleGroupRelation;
        JPAQuery<RoleGroup> roleGroupJPAQuery = jpaQueryFactory.select(roleGroupService.getBaseBean(_Q_RoleGroup)).from(_Q_RoleGroup);
        roleGroupJPAQuery.leftJoin(_Q_RoleGroup.userRoleGroupRelations, _Q_UserRoleGroupRelation).leftJoin(_Q_UserRoleGroupRelation.user, _Q_User);
        Predicate predicate = _Q_User.id.eq(id);
        roleGroupJPAQuery.where(predicate);
        roleGroupService.orderBy(pageable, _Q_RoleGroup, roleGroupJPAQuery);
        queryDslTools.addPageable(pageable, roleGroupJPAQuery);
        QueryResults<RoleGroup> result = roleGroupJPAQuery.fetchResults();
        Page<RoleGroupBaseBO> roleGroupBaseBOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return roleGroupBaseBOPage;
    }

    @Override
    public Long batchAddRoleGroup(String id, RelationsBO relationsBO) throws Exception {
        QUser _Q_User = QUser.user;
        QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
        User user = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(user)) {
            throw new NotExistException("账号数据不存在");
        }
        List<RoleGroup> roleGroups = jpaQueryFactory.selectFrom(_Q_RoleGroup).where(_Q_RoleGroup.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(roleGroups)) {
            for (RoleGroup roleGroup : roleGroups) {
                UserRoleGroupRelation userRoleGroupRelation = new UserRoleGroupRelation();
                userRoleGroupRelation.setUser(user);
                userRoleGroupRelation.setRoleGroup(roleGroup);
                entityManager.persist(userRoleGroupRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(roleGroups) ? roleGroups.size() : 0);
    }

    @Override
    public void batchRemoveRoleGroup(String id, RelationsBO relationsBO) throws Exception {
        QUserRoleGroupRelation _Q_UserRoleGroupRelation = QUserRoleGroupRelation.userRoleGroupRelation;
        JPADeleteClause teamRoleGroupRelationJPAQuery = jpaQueryFactory.delete(_Q_UserRoleGroupRelation);
        Predicate predicate = _Q_UserRoleGroupRelation.user.id.eq(id).and(_Q_UserRoleGroupRelation.roleGroup.id.in(relationsBO.getRelationsIds()));
        teamRoleGroupRelationJPAQuery.where(predicate);
        teamRoleGroupRelationJPAQuery.execute();
    }

    @Override
    public Page<TeamBaseBO> queryTeam(String id, Pageable pageable) throws Exception {
        QUser _Q_User = QUser.user;
        QTeam _Q_Team = QTeam.team;
        QTeamUserRelation _Q_TeamUserRelation = QTeamUserRelation.teamUserRelation;
        JPAQuery<Team> teamJPAQuery = jpaQueryFactory.select(teamService.getBaseBean(_Q_Team)).from(_Q_Team);
        teamJPAQuery.leftJoin(_Q_Team.teamUserRelations, _Q_TeamUserRelation).leftJoin(_Q_TeamUserRelation.user, _Q_User);
        Predicate predicate = _Q_User.id.eq(id);
        teamJPAQuery.where(predicate);
        teamService.orderBy(pageable, _Q_Team, teamJPAQuery);
        queryDslTools.addPageable(pageable, teamJPAQuery);
        QueryResults<Team> result = teamJPAQuery.fetchResults();
        Page<TeamBaseBO> teamBaseBOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return teamBaseBOPage;
    }

    @Override
    public Long batchAddTeam(String id, RelationsBO relationsBO) throws Exception {
        QUser _Q_User = QUser.user;
        QTeam _Q_Team = QTeam.team;
        User user = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(user)) {
            throw new NotExistException("账号数据不存在");
        }
        List<Team> teams = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(teams)) {
            for (Team team : teams) {
                TeamUserRelation teamUserRelation = new TeamUserRelation();
                teamUserRelation.setUser(user);
                teamUserRelation.setTeam(team);
                entityManager.persist(teamUserRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(teams) ? teams.size() : 0);
    }

    @Override
    public void batchRemoveTeam(String id, RelationsBO relationsBO) throws Exception {
        QTeamUserRelation _Q_TeamUserRelation = QTeamUserRelation.teamUserRelation;
        JPADeleteClause teamRoleGroupRelationJPAQuery = jpaQueryFactory.delete(_Q_TeamUserRelation);
        Predicate predicate = _Q_TeamUserRelation.user.id.eq(id).and(_Q_TeamUserRelation.team.id.in(relationsBO.getRelationsIds()));
        teamRoleGroupRelationJPAQuery.where(predicate);
        teamRoleGroupRelationJPAQuery.execute();
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

}
