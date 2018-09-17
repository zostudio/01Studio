package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.TeamParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.domain.*;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.base.RoleGroupBaseMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
import com.zos.security.rbac.mapper.detail.TeamDetailMapper;
import com.zos.security.rbac.repository.TeamRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RoleGroupService;
import com.zos.security.rbac.service.RoleService;
import com.zos.security.rbac.service.TeamService;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private EntityManager entityManager;

	@Autowired
    private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleGroupService roleGroupService;

	@Autowired
    private QueryDslTools queryDslTools;

	@Override
	public TeamBaseBO create(TeamBaseBO teamBaseBO) {
		return TeamBaseMapper.INSTANCE.domainToBO(teamRepository.save(TeamBaseMapper.INSTANCE.boToDomain(teamBaseBO)));
	}

	@Override
	public TeamBaseBO update(String id, TeamBaseBO teamBaseBO) {
        QTeam _Q_Team = QTeam.team;
        Long count = jpaQueryFactory.select(_Q_Team.id.count()).where(_Q_Team.id.eq(id)).fetchOne();
        if (ConstantValidator.isValueless(count)) {
            throw new NotExistException("团队数据不存在");
        }
        jpaQueryFactory.update(_Q_Team).where(_Q_Team.id.eq(id))
                .set(_Q_Team.name, teamBaseBO.getName())
                .set(_Q_Team.description, teamBaseBO.getDescription())
                .execute();
        teamBaseBO.setId(id);
		return teamBaseBO;
	}

	@Override
	public void delete(String id) {
		teamRepository.delete(id);
	}

	@Override
	public TeamDetailBO getInfo(String id) {
        QTeam _Q_Team = QTeam.team;
        Team team = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.eq(id)).fetchOne();
		return TeamDetailMapper.INSTANCE.domainToBO(team);
	}

	@Override
	public Page<TeamBaseBO> queryBase(TeamParamBaseBO teamParamBaseBO, Pageable pageable) {
		QTeam _Q_Team = QTeam.team;
		JPAQuery<Team> teamJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Team)).from(_Q_Team);
        Predicate predicate = _Q_Team.isNotNull();
        predicate = addBaseWhere(predicate, teamParamBaseBO, _Q_Team, teamJPAQuery);
        teamJPAQuery.where(predicate);
        orderBy(pageable, _Q_Team, teamJPAQuery);
        queryDslTools.addPageable(pageable, teamJPAQuery);
        QueryResults<Team> result = teamJPAQuery.fetchResults();
		Page<TeamBaseBO> teamBaseBOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return teamBaseBOPage;
	}

    @Override
	public Page<TeamDetailBO> queryDetail(TeamParamDetailBO teamParamDetailBO, Pageable pageable) {
        QTeam _Q_Team = QTeam.team;
        JPAQuery<Team> teamJPAQuery = jpaQueryFactory.selectFrom(_Q_Team);
        Predicate predicate = _Q_Team.isNotNull();
        predicate = addDetailWhere(predicate, teamParamDetailBO, _Q_Team, teamJPAQuery);
        teamJPAQuery.where(predicate);
        orderBy(pageable, _Q_Team, teamJPAQuery);
        queryDslTools.addPageable(pageable, teamJPAQuery);
        QueryResults<Team> result = teamJPAQuery.fetchResults();
        Page<TeamDetailBO> teamDetailBOPage = QueryResultConverter.convert(TeamDetailMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return teamDetailBOPage;
	}

    @Override
    public QBean<Team> getBaseBean(QTeam _Q_Team) {
        return Projections.bean(Team.class, _Q_Team.id, _Q_Team.name, _Q_Team.description);
    }

    @Override
    public void orderBy(Pageable pageable, QTeam _Q_Team, JPAQuery<Team> teamJPAQuery) {
        if (ConstantValidator.isNotNull(pageable.getSort())) {
            pageable.getSort().forEach(order -> {
                if (ConstantValidator.isNotNull(order) && StringUtils.isNotEmpty(order.getProperty())) {
                    switch(order.getProperty()) {
                        case "name":
                            teamJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Team.name, queryDslTools.getNullHandling(order)));
                            break;
                        case "description":
                            teamJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Team.description, queryDslTools.getNullHandling(order)));
                            break;
                        case "id":
                            teamJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Team.id, queryDslTools.getNullHandling(order)));
                            break;
                        case "createdDate":
                            teamJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Team.createdDate, queryDslTools.getNullHandling(order)));
                            break;
                        case "createdBy":
                            teamJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Team.createdBy, queryDslTools.getNullHandling(order)));
                            break;
                        case "lastModifiedDate":
                            teamJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Team.lastModifiedDate, queryDslTools.getNullHandling(order)));
                            break;
                        case "lastModifiedBy":
                            teamJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Team.lastModifiedBy, queryDslTools.getNullHandling(order)));
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void changeParent(String parentId, String id) throws Exception {
        Team parentTeam = teamRepository.findOne(parentId);
        if (ConstantValidator.isNull(parentTeam)) {
            throw new NotExistException("父团队数据不存在");
        }
        Team childTeam = teamRepository.findOne(id);
        if (ConstantValidator.isNull(childTeam)) {
            throw new NotExistException("子团队数据不存在");
        }
        childTeam.setParent(parentTeam);
        teamRepository.save(childTeam);
    }

    @Override
    public Page<TeamBaseBO> queryByParentId(String parentId, Pageable pageable) throws Exception {
        QTeam _Q_Team = QTeam.team;
        JPAQuery<Team> teamJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Team)).from(_Q_Team);
        Predicate predicate = _Q_Team.parent.id.eq(parentId);
        teamJPAQuery.where(predicate);
        orderBy(pageable, _Q_Team, teamJPAQuery);
        queryDslTools.addPageable(pageable, teamJPAQuery);
        QueryResults<Team> result = teamJPAQuery.fetchResults();
        Page<TeamBaseBO> teamBaseBOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return teamBaseBOPage;
    }

    @Override
    public TeamBaseBO queryParentById(String id) throws Exception {
        Team childTeam = teamRepository.findOne(id);
        if (ConstantValidator.isNull(childTeam)) {
            throw new NotExistException("子团队数据不存在");
        }
        if (childTeam.getParent() == null) {
            throw new NotExistException("父团队数据不存在");
        }
        return TeamBaseMapper.INSTANCE.domainToBO(childTeam.getParent());
    }

    @Override
    public void deleteByParentId(String parentId) throws Exception {
        Team parentTeam = teamRepository.findOne(parentId);
        if (ConstantValidator.isNull(parentTeam)) {
            throw new NotExistException("父团队数据不存在");
        }
        if (CollectionUtils.isNotEmpty(parentTeam.getChildren())) {
            teamRepository.deleteInBatch(parentTeam.getChildren());
        }
    }

    @Override
    public Page<UserBaseBO> queryUser(String id, Pageable pageable) throws Exception {
        QTeam _Q_Team = QTeam.team;
        QUser _Q_User = QUser.user;
        QTeamUserRelation _Q_TeamUserRelation = QTeamUserRelation.teamUserRelation;
        JPAQuery<User> userJPAQuery = jpaQueryFactory.select(userService.getBaseBean(_Q_User)).from(_Q_User);
        userJPAQuery.leftJoin(_Q_User.teamUserRelations, _Q_TeamUserRelation).leftJoin(_Q_TeamUserRelation.team, _Q_Team);
        Predicate predicate = _Q_Team.id.eq(id);
        userJPAQuery.where(predicate);
        userService.orderBy(pageable, _Q_User, userJPAQuery);
        queryDslTools.addPageable(pageable, userJPAQuery);
        QueryResults<User> result = userJPAQuery.fetchResults();
        Page<UserBaseBO> userBaseBOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return userBaseBOPage;
    }

    @Override
    public Long batchAddUser(String id, RelationsBO relationsBO) throws Exception {
        QTeam _Q_Team = QTeam.team;
        QUser _Q_User = QUser.user;
        Team team = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(team)) {
            throw new NotExistException("团队数据不存在");
        }
        List<User> users = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(users)) {
            for (User user : users) {
                TeamUserRelation teamUserRelation = new TeamUserRelation();
                teamUserRelation.setTeam(team);
                teamUserRelation.setUser(user);
                entityManager.persist(teamUserRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(users) ? users.size() : 0);
    }

    @Override
    public void batchRemoveUser(String id, RelationsBO relationsBO) throws Exception {
        QTeamUserRelation _Q_TeamUserRelation = QTeamUserRelation.teamUserRelation;
        JPADeleteClause teamUserRelationJPAQuery = jpaQueryFactory.delete(_Q_TeamUserRelation);
        Predicate predicate = _Q_TeamUserRelation.team.id.eq(id).and(_Q_TeamUserRelation.user.id.in(relationsBO.getRelationsIds()));
        teamUserRelationJPAQuery.where(predicate);
        teamUserRelationJPAQuery.execute();
    }

    @Override
    public Page<RoleBaseBO> queryRole(String id, Pageable pageable) throws Exception {
        QTeam _Q_Team = QTeam.team;
        QRole _Q_Role = QRole.role;
        QTeamRoleRelation _Q_TeamRoleRelation = QTeamRoleRelation.teamRoleRelation;
        JPAQuery<Role> roleJPAQuery = jpaQueryFactory.select(roleService.getBaseBean(_Q_Role)).from(_Q_Role);
        roleJPAQuery.leftJoin(_Q_Role.teamRoleRelations, _Q_TeamRoleRelation).leftJoin(_Q_TeamRoleRelation.team, _Q_Team);
        Predicate predicate = _Q_Team.id.eq(id);
        roleJPAQuery.where(predicate);
        roleService.orderBy(pageable, _Q_Role, roleJPAQuery);
        queryDslTools.addPageable(pageable, roleJPAQuery);
        QueryResults<Role> result = roleJPAQuery.fetchResults();
        Page<RoleBaseBO> roleBaseBOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return roleBaseBOPage;
    }

    @Override
    public Long batchAddRole(String id, RelationsBO relationsBO) throws Exception {
        QTeam _Q_Team = QTeam.team;
        QRole _Q_Role = QRole.role;
        Team team = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(team)) {
            throw new NotExistException("团队数据不存在");
        }
        List<Role> roles = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                TeamRoleRelation teamRoleRelation = new TeamRoleRelation();
                teamRoleRelation.setTeam(team);
                teamRoleRelation.setRole(role);
                entityManager.persist(teamRoleRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(roles) ? roles.size() : 0);
    }

    @Override
    public void batchRemoveRole(String id, RelationsBO relationsBO) throws Exception {
        QTeamRoleRelation _Q_TeamRoleRelation = QTeamRoleRelation.teamRoleRelation;
        JPADeleteClause teamRoleRelationJPAQuery = jpaQueryFactory.delete(_Q_TeamRoleRelation);
        Predicate predicate = _Q_TeamRoleRelation.team.id.eq(id).and(_Q_TeamRoleRelation.role.id.in(relationsBO.getRelationsIds()));
        teamRoleRelationJPAQuery.where(predicate);
        teamRoleRelationJPAQuery.execute();
    }

    @Override
    public Page<RoleGroupBaseBO> queryRoleGroup(String id, Pageable pageable) throws Exception {
        QTeam _Q_Team = QTeam.team;
        QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
        QTeamRoleGroupRelation _Q_TeamRoleGroupRelation = QTeamRoleGroupRelation.teamRoleGroupRelation;
        JPAQuery<RoleGroup> roleGroupJPAQuery = jpaQueryFactory.select(roleGroupService.getBaseBean(_Q_RoleGroup)).from(_Q_RoleGroup);
        roleGroupJPAQuery.leftJoin(_Q_RoleGroup.teamRoleGroupRelations, _Q_TeamRoleGroupRelation).leftJoin(_Q_TeamRoleGroupRelation.team, _Q_Team);
        Predicate predicate = _Q_Team.id.eq(id);
        roleGroupJPAQuery.where(predicate);
        roleGroupService.orderBy(pageable, _Q_RoleGroup, roleGroupJPAQuery);
        queryDslTools.addPageable(pageable, roleGroupJPAQuery);
        QueryResults<RoleGroup> result = roleGroupJPAQuery.fetchResults();
        Page<RoleGroupBaseBO> roleGroupBaseBOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return roleGroupBaseBOPage;
    }

    @Override
    public Long batchAddRoleGroup(String id, RelationsBO relationsBO) throws Exception {
        QTeam _Q_Team = QTeam.team;
        QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
        Team team = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(team)) {
            throw new NotExistException("团队数据不存在");
        }
        List<RoleGroup> roleGroups = jpaQueryFactory.selectFrom(_Q_RoleGroup).where(_Q_RoleGroup.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(roleGroups)) {
            for (RoleGroup roleGroup : roleGroups) {
                TeamRoleGroupRelation teamRoleGroupRelation = new TeamRoleGroupRelation();
                teamRoleGroupRelation.setTeam(team);
                teamRoleGroupRelation.setRoleGroup(roleGroup);
                entityManager.persist(teamRoleGroupRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(roleGroups) ? roleGroups.size() : 0);
    }

    @Override
    public void batchRemoveRoleGroup(String id, RelationsBO relationsBO) throws Exception {
        QTeamRoleGroupRelation _Q_TeamRoleGroupRelation = QTeamRoleGroupRelation.teamRoleGroupRelation;
        JPADeleteClause teamRoleGroupRelationJPAQuery = jpaQueryFactory.delete(_Q_TeamRoleGroupRelation);
        Predicate predicate = _Q_TeamRoleGroupRelation.team.id.eq(id).and(_Q_TeamRoleGroupRelation.roleGroup.id.in(relationsBO.getRelationsIds()));
        teamRoleGroupRelationJPAQuery.where(predicate);
        teamRoleGroupRelationJPAQuery.execute();
    }

    private Predicate addBaseWhere(Predicate predicate, TeamParamBaseBO teamParamBaseBO, QTeam _Q_Team, JPAQuery<Team> teamJPAQuery) {
        if (StringUtils.isNotEmpty(teamParamBaseBO.getName())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.name.like("%" + teamParamBaseBO.getName() + "%"));
        }
        if (StringUtils.isNotEmpty(teamParamBaseBO.getDescription())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.description.like("%" + teamParamBaseBO.getDescription() + "%"));
        }
        return predicate;
    }

    private Predicate addDetailWhere(Predicate predicate, TeamParamDetailBO teamParamDetailBO, QTeam _Q_Team, JPAQuery<Team> teamJPAQuery) {
        predicate = addBaseWhere(predicate, teamParamDetailBO, _Q_Team, teamJPAQuery);
        if (ConstantValidator.isNotNull(teamParamDetailBO.getCreatedDateStart())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.createdDate.goe(teamParamDetailBO.getCreatedDateStart()));
        }
        if (ConstantValidator.isNotNull(teamParamDetailBO.getCreatedDateEnd())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.createdDate.loe(teamParamDetailBO.getCreatedDateEnd()));
        }
        if (ConstantValidator.isNotNull(teamParamDetailBO.getLastModifiedDateStart())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.lastModifiedDate.goe(teamParamDetailBO.getLastModifiedDateStart()));
        }
        if (ConstantValidator.isNotNull(teamParamDetailBO.getLastModifiedDateEnd())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.lastModifiedDate.loe(teamParamDetailBO.getLastModifiedDateEnd()));
        }
        if (ConstantValidator.isValuable(teamParamDetailBO.getCreatedBy())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.createdBy.eq(teamParamDetailBO.getCreatedBy()));
        }
        if (ConstantValidator.isValuable(teamParamDetailBO.getLastModifiedBy())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.lastModifiedBy.eq(teamParamDetailBO.getLastModifiedBy()));
        }
        return predicate;
    }
}
