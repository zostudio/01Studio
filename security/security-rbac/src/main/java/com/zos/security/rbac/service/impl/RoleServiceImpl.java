package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.base.RoleParamBaseBO;
import com.zos.security.rbac.bo.param.common.RelationsBO;
import com.zos.security.rbac.bo.param.detail.RoleParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.*;
import com.zos.security.rbac.bo.resopnse.detail.RoleDetailBO;
import com.zos.security.rbac.domain.*;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.base.*;
import com.zos.security.rbac.mapper.detail.RoleDetailMapper;
import com.zos.security.rbac.repository.RoleRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.*;
import com.zos.security.rbac.support.enums.RoleType;
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
public class RoleServiceImpl implements RoleService {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private QueryDslTools queryDslTools;

	@Autowired
    private EntityManager entityManager;

	@Autowired
    private UserService userService;

	@Autowired
    private RoleGroupService roleGroupService;

	@Autowired
    private ResourceService resourceService;

	@Autowired
    private TeamService teamService;

	@Override
	public RoleBaseBO create(RoleBaseBO roleBaseBO) {
		return RoleBaseMapper.INSTANCE.domainToBO(roleRepository.save(RoleBaseMapper.INSTANCE.boToDomain(roleBaseBO)));
	}

	@Override
	public RoleBaseBO update(String id, RoleBaseBO roleBaseBO) {
		QRole _Q_Role = QRole.role;
		Long count = jpaQueryFactory.select(_Q_Role.id.count()).where(_Q_Role.id.eq(id)).fetchOne();
		if (ConstantValidator.isValueless(count)) {
			throw new NotExistException("角色数据不存在");
		}
		jpaQueryFactory.update(_Q_Role).where(_Q_Role.id.eq(id))
				.set(_Q_Role.name, roleBaseBO.getName())
				.set(_Q_Role.type, roleBaseBO.getType())
				.set(_Q_Role.description, roleBaseBO.getDescription())
				.execute();
		roleBaseBO.setId(id);
		return roleBaseBO;
	}

	@Override
	public void delete(String id) {
		roleRepository.delete(id);
	}

	@Override
	public RoleDetailBO getInfo(String id) {
		QRole _Q_Role = QRole.role;
		Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();;
		return RoleDetailMapper.INSTANCE.domainToBO(role);
	}

	@Override
	public Page<RoleBaseBO> queryBase(RoleParamBaseBO roleParamBaseBO, Pageable pageable) {
		QRole _Q_Role = QRole.role;
		JPAQuery<Role> roleJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Role)).from(_Q_Role);
		Predicate predicate = _Q_Role.isNotNull();
		predicate = addBaseWhere(predicate, roleParamBaseBO, _Q_Role, roleJPAQuery);
		roleJPAQuery.where(predicate);
		orderBy(pageable, _Q_Role, roleJPAQuery);
		queryDslTools.addPageable(pageable, roleJPAQuery);
		QueryResults<Role> result = roleJPAQuery.fetchResults();
		Page<RoleBaseBO> roleBaseBOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleBaseBOPage;
	}

	@Override
	public Page<RoleDetailBO> queryDetail(RoleParamDetailBO roleParamDetailBO, Pageable pageable) {
		QRole _Q_Role = QRole.role;
		JPAQuery<Role> roleJPAQuery = jpaQueryFactory.selectFrom(_Q_Role);
		Predicate predicate = _Q_Role.isNotNull();
		predicate = addDetailWhere(predicate, roleParamDetailBO, _Q_Role, roleJPAQuery);
		roleJPAQuery.where(predicate);
		orderBy(pageable, _Q_Role, roleJPAQuery);
		queryDslTools.addPageable(pageable, roleJPAQuery);
		QueryResults<Role> result = roleJPAQuery.fetchResults();
		Page<RoleDetailBO> roleDetailBOPage = QueryResultConverter.convert(RoleDetailMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return roleDetailBOPage;
	}

	@Override
	public QBean<Role> getBaseBean(QRole _Q_Role) {
		return Projections.bean(
				Role.class,
				_Q_Role.id,
				_Q_Role.name,
				_Q_Role.type,
				_Q_Role.description);
	}

	@Override
	public void orderBy(Pageable pageable, QRole _Q_Role, JPAQuery<Role> roleJPAQuery) {
		if (ConstantValidator.isNotNull(pageable.getSort())) {
			pageable.getSort().forEach(order -> {
				if (ConstantValidator.isNotNull(order) && StringUtils.isNotEmpty(order.getProperty())) {
					switch(order.getProperty()) {
						case "name":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.name, queryDslTools.getNullHandling(order)));
							break;
						case "type":
							roleJPAQuery.orderBy(new OrderSpecifier<RoleType>(queryDslTools.getOrder(order), _Q_Role.type, queryDslTools.getNullHandling(order)));
							break;
						case "description":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.description, queryDslTools.getNullHandling(order)));
							break;
						case "id":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.id, queryDslTools.getNullHandling(order)));
							break;
						case "createdDate":
							roleJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Role.createdDate, queryDslTools.getNullHandling(order)));
							break;
						case "createdBy":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.createdBy, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedDate":
							roleJPAQuery.orderBy(new OrderSpecifier<Date>(queryDslTools.getOrder(order), _Q_Role.lastModifiedDate, queryDslTools.getNullHandling(order)));
							break;
						case "lastModifiedBy":
							roleJPAQuery.orderBy(new OrderSpecifier<String>(queryDslTools.getOrder(order), _Q_Role.lastModifiedBy, queryDslTools.getNullHandling(order)));
							break;
					}
				}
			});
		}
	}

	@Override
	public Page<UserBaseBO> queryUser(String id, Pageable pageable) throws Exception {
		QRole _Q_Role = QRole.role;
		QUser _Q_User = QUser.user;
		QUserRoleRelation _Q_UserRoleRelation = QUserRoleRelation.userRoleRelation;
		JPAQuery<User> userJPAQuery = jpaQueryFactory.select(userService.getBaseBean(_Q_User)).from(_Q_User);
		userJPAQuery.leftJoin(_Q_User.userRoleRelations, _Q_UserRoleRelation).leftJoin(_Q_UserRoleRelation.role, _Q_Role);
		Predicate predicate = _Q_Role.id.eq(id);
		userJPAQuery.where(predicate);
		userService.orderBy(pageable, _Q_User, userJPAQuery);
		queryDslTools.addPageable(pageable, userJPAQuery);
		QueryResults<User> result = userJPAQuery.fetchResults();
		Page<UserBaseBO> userBaseBOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return userBaseBOPage;
	}

	@Override
	public Long batchAddUser(String id, RelationsBO relationsBO) throws Exception {
        QRole _Q_Role = QRole.role;
        QUser _Q_User = QUser.user;
        Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(role)) {
            throw new NotExistException("角色数据不存在");
        }
        List<User> users = jpaQueryFactory.selectFrom(_Q_User).where(_Q_User.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(users)) {
            for (User user : users) {
                UserRoleRelation userRoleRelation = new UserRoleRelation();
                userRoleRelation.setRole(role);
                userRoleRelation.setUser(user);
                entityManager.persist(userRoleRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(users) ? users.size() : 0);
	}

	@Override
	public void batchRemoveUser(String id, RelationsBO relationsBO) throws Exception {
        QUserRoleRelation _Q_UserRoleRelation = QUserRoleRelation.userRoleRelation;
        JPADeleteClause teamUserRelationJPAQuery = jpaQueryFactory.delete(_Q_UserRoleRelation);
        Predicate predicate = _Q_UserRoleRelation.role.id.eq(id).and(_Q_UserRoleRelation.user.id.in(relationsBO.getRelationsIds()));
        teamUserRelationJPAQuery.where(predicate);
        teamUserRelationJPAQuery.execute();
	}

	@Override
	public Page<RoleGroupBaseBO> queryRoleGroup(String id, Pageable pageable) throws Exception {
        QRole _Q_Role = QRole.role;
        QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
        QRoleGroupRoleRelation _Q_RoleGroupRoleRelation = QRoleGroupRoleRelation.roleGroupRoleRelation;
        JPAQuery<RoleGroup> roleGroupJPAQuery = jpaQueryFactory.select(roleGroupService.getBaseBean(_Q_RoleGroup)).from(_Q_RoleGroup);
        roleGroupJPAQuery.leftJoin(_Q_RoleGroup.roleGroupRoleRelations, _Q_RoleGroupRoleRelation).leftJoin(_Q_RoleGroupRoleRelation.role, _Q_Role);
        Predicate predicate = _Q_Role.id.eq(id);
        roleGroupJPAQuery.where(predicate);
        roleGroupService.orderBy(pageable, _Q_RoleGroup, roleGroupJPAQuery);
        queryDslTools.addPageable(pageable, roleGroupJPAQuery);
        QueryResults<RoleGroup> result = roleGroupJPAQuery.fetchResults();
        Page<RoleGroupBaseBO> roleGroupBaseBOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return roleGroupBaseBOPage;
	}

	@Override
	public Long batchAddRoleGroup(String id, RelationsBO relationsBO) throws Exception {
        QRole _Q_Role = QRole.role;
        QRoleGroup _Q_RoleGroup = QRoleGroup.roleGroup;
        Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(role)) {
            throw new NotExistException("角色数据不存在");
        }
        List<RoleGroup> roleGroups = jpaQueryFactory.selectFrom(_Q_RoleGroup).where(_Q_RoleGroup.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(roleGroups)) {
            for (RoleGroup roleGroup : roleGroups) {
                RoleGroupRoleRelation roleGroupRoleRelation = new RoleGroupRoleRelation();
                roleGroupRoleRelation.setRole(role);
                roleGroupRoleRelation.setRoleGroup(roleGroup);
                entityManager.persist(roleGroupRoleRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(roleGroups) ? roleGroups.size() : 0);
	}

	@Override
	public void batchRemoveRoleGroup(String id, RelationsBO relationsBO) throws Exception {
        QRoleGroupRoleRelation _Q_RoleGroupRoleRelation = QRoleGroupRoleRelation.roleGroupRoleRelation;
        JPADeleteClause teamRoleGroupRelationJPAQuery = jpaQueryFactory.delete(_Q_RoleGroupRoleRelation);
        Predicate predicate = _Q_RoleGroupRoleRelation.role.id.eq(id).and(_Q_RoleGroupRoleRelation.roleGroup.id.in(relationsBO.getRelationsIds()));
        teamRoleGroupRelationJPAQuery.where(predicate);
        teamRoleGroupRelationJPAQuery.execute();
	}

	@Override
	public Page<ResourceBaseBO> queryResource(String id, Pageable pageable) throws Exception {
        QRole _Q_Role = QRole.role;
        QResource _Q_Resource = QResource.resource;
        QRoleResourceRelation _Q_RoleResourceRelation = QRoleResourceRelation.roleResourceRelation;
        JPAQuery<Resource> resourceJPAQuery = jpaQueryFactory.select(resourceService.getBaseBean(_Q_Resource)).from(_Q_Resource);
        resourceJPAQuery.leftJoin(_Q_Resource.roleResourceRelations, _Q_RoleResourceRelation).leftJoin(_Q_RoleResourceRelation.role, _Q_Role);
        Predicate predicate = _Q_Role.id.eq(id);
        resourceJPAQuery.where(predicate);
        resourceService.orderBy(pageable, _Q_Resource, resourceJPAQuery);
        queryDslTools.addPageable(pageable, resourceJPAQuery);
        QueryResults<Resource> result = resourceJPAQuery.fetchResults();
        Page<ResourceBaseBO> resourceBaseBOPage = QueryResultConverter.convert(ResourceBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return resourceBaseBOPage;
	}

	@Override
	public Long batchAddResource(String id, RelationsBO relationsBO) throws Exception {
        QRole _Q_Role = QRole.role;
        QResource _Q_Resource = QResource.resource;
        Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(role)) {
            throw new NotExistException("角色数据不存在");
        }
        List<Resource> resources = jpaQueryFactory.selectFrom(_Q_Resource).where(_Q_Resource.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(resources)) {
            for (Resource resource : resources) {
                RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
                roleResourceRelation.setRole(role);
                roleResourceRelation.setResource(resource);
                entityManager.persist(roleResourceRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(resources) ? resources.size() : 0);
	}

	@Override
	public void batchRemoveResource(String id, RelationsBO relationsBO) throws Exception {
        QRoleResourceRelation _Q_RoleResourceRelation = QRoleResourceRelation.roleResourceRelation;
        JPADeleteClause teamRoleGroupRelationJPAQuery = jpaQueryFactory.delete(_Q_RoleResourceRelation);
        Predicate predicate = _Q_RoleResourceRelation.role.id.eq(id).and(_Q_RoleResourceRelation.resource.id.in(relationsBO.getRelationsIds()));
        teamRoleGroupRelationJPAQuery.where(predicate);
        teamRoleGroupRelationJPAQuery.execute();
	}

	@Override
	public Page<TeamBaseBO> queryTeam(String id, Pageable pageable) throws Exception {
        QRole _Q_Role = QRole.role;
        QTeam _Q_Team = QTeam.team;
        QTeamRoleRelation _Q_TeamRoleRelation = QTeamRoleRelation.teamRoleRelation;
        JPAQuery<Team> teamJPAQuery = jpaQueryFactory.select(teamService.getBaseBean(_Q_Team)).from(_Q_Team);
        teamJPAQuery.leftJoin(_Q_Team.teamRoleRelations, _Q_TeamRoleRelation).leftJoin(_Q_TeamRoleRelation.role, _Q_Role);
        Predicate predicate = _Q_Role.id.eq(id);
        teamJPAQuery.where(predicate);
        teamService.orderBy(pageable, _Q_Team, teamJPAQuery);
        queryDslTools.addPageable(pageable, teamJPAQuery);
        QueryResults<Team> result = teamJPAQuery.fetchResults();
        Page<TeamBaseBO> teamBaseBOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return teamBaseBOPage;
	}

	@Override
	public Long batchAddTeam(String id, RelationsBO relationsBO) throws Exception {
        QRole _Q_Role = QRole.role;
        QTeam _Q_Team = QTeam.team;
        Role role = jpaQueryFactory.selectFrom(_Q_Role).where(_Q_Role.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(role)) {
            throw new NotExistException("角色数据不存在");
        }
        List<Team> teams = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.in(relationsBO.getRelationsIds())).fetch();
        if (CollectionUtils.isNotEmpty(teams)) {
            for (Team team : teams) {
                TeamRoleRelation teamRoleRelation = new TeamRoleRelation();
                teamRoleRelation.setRole(role);
                teamRoleRelation.setTeam(team);
                entityManager.persist(teamRoleRelation);
            }
        }
        return Long.valueOf(CollectionUtils.isNotEmpty(teams) ? teams.size() : 0);
	}

	@Override
	public void batchRemoveTeam(String id, RelationsBO relationsBO) throws Exception {
        QTeamRoleRelation _Q_TeamRoleRelation = QTeamRoleRelation.teamRoleRelation;
        JPADeleteClause teamRoleGroupRelationJPAQuery = jpaQueryFactory.delete(_Q_TeamRoleRelation);
        Predicate predicate = _Q_TeamRoleRelation.role.id.eq(id).and(_Q_TeamRoleRelation.team.id.in(relationsBO.getRelationsIds()));
        teamRoleGroupRelationJPAQuery.where(predicate);
        teamRoleGroupRelationJPAQuery.execute();
	}

	private Predicate addBaseWhere(Predicate predicate, RoleParamBaseBO roleParamBaseBO, QRole _Q_Role, JPAQuery<Role> roleJPAQuery) {
		if (StringUtils.isNotEmpty(roleParamBaseBO.getName())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.name.like("%" + roleParamBaseBO.getName() + "%"));
		}
		if (ConstantValidator.isNotNull(roleParamBaseBO.getType())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.type.eq(roleParamBaseBO.getType()));
		}
		if (StringUtils.isNotEmpty(roleParamBaseBO.getDescription())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.description.like("%" + roleParamBaseBO.getDescription() + "%"));
		}
		return predicate;
	}

	private Predicate addDetailWhere(Predicate predicate, RoleParamDetailBO roleParamDetailBO, QRole _Q_Role, JPAQuery<Role> roleJPAQuery) {
		predicate = addBaseWhere(predicate, roleParamDetailBO, _Q_Role, roleJPAQuery);
		if (ConstantValidator.isNotNull(roleParamDetailBO.getCreatedDateStart())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.createdDate.goe(roleParamDetailBO.getCreatedDateStart()));
		}
		if (ConstantValidator.isNotNull(roleParamDetailBO.getCreatedDateEnd())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.createdDate.loe(roleParamDetailBO.getCreatedDateEnd()));
		}
		if (ConstantValidator.isNotNull(roleParamDetailBO.getLastModifiedDateStart())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.lastModifiedDate.goe(roleParamDetailBO.getLastModifiedDateStart()));
		}
		if (ConstantValidator.isNotNull(roleParamDetailBO.getLastModifiedDateEnd())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.lastModifiedDate.loe(roleParamDetailBO.getLastModifiedDateEnd()));
		}
		if (ConstantValidator.isValuable(roleParamDetailBO.getCreatedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.createdBy.eq(roleParamDetailBO.getCreatedBy()));
		}
		if (ConstantValidator.isValuable(roleParamDetailBO.getLastModifiedBy())) {
			predicate = ExpressionUtils.and(predicate, _Q_Role.lastModifiedBy.eq(roleParamDetailBO.getLastModifiedBy()));
		}
		return predicate;
	}
}
