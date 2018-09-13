package com.zos.security.rbac.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zos.security.rbac.bo.param.detail.TeamParamDetailBO;
import com.zos.security.rbac.bo.param.base.TeamParamBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.TeamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.domain.QTeam;
import com.zos.security.rbac.domain.Team;
import com.zos.security.rbac.exception.common.NotExistException;
import com.zos.security.rbac.mapper.detail.TeamDetailMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
import com.zos.security.rbac.repository.TeamRepository;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.TeamService;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.utils.QueryDslTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
    private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
    private QueryDslTools queryDslTools;

	@Override
	public TeamBaseBO create(TeamBaseBO teamBaseBO) {
		return TeamBaseMapper.INSTANCE.domainToBO(teamRepository.save(TeamBaseMapper.INSTANCE.boToDomain(teamBaseBO)));
	}

	@Override
	public TeamBaseBO update(String id, TeamBaseBO teamBaseBO) {
        QTeam _Q_Team = QTeam.team;
        Team team = jpaQueryFactory.selectFrom(_Q_Team).where(_Q_Team.id.eq(id)).fetchOne();
        if (ConstantValidator.isNull(team)) {
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
	public Page<TeamBaseBO> querySimple(TeamParamBaseBO teamParamBaseBO, Pageable pageable) {
		QTeam _Q_Team = QTeam.team;
		JPAQuery<Team> teamJPAQuery = jpaQueryFactory.select(getBaseBean(_Q_Team)).from(_Q_Team);
        Predicate predicate = _Q_Team.isNotNull();
        predicate = addBaseWhere(predicate, teamParamBaseBO, _Q_Team, teamJPAQuery);
        teamJPAQuery.where(predicate);
        orderBy(pageable, _Q_Team, teamJPAQuery);
        queryDslTools.addPageable(pageable, teamJPAQuery);
        QueryResults<Team> result = teamJPAQuery.fetchResults();
		Page<TeamBaseBO> teamSimpleBOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
		return teamSimpleBOPage;
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
        Page<TeamBaseBO> teamSimpleBOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.domainToBO(result.getResults()), pageable, result.getTotal());
        return teamSimpleBOPage;
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

    private QBean<Team> getBaseBean(QTeam _Q_Team) {
        return Projections.bean(Team.class, _Q_Team.id, _Q_Team.name, _Q_Team.description);
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
        if (teamParamDetailBO.getCreatedDateStart() != null) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.createdDate.goe(teamParamDetailBO.getCreatedDateStart()));
        }
        if (teamParamDetailBO.getCreatedDateEnd() != null) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.createdDate.loe(teamParamDetailBO.getCreatedDateEnd()));
        }
        if (teamParamDetailBO.getLastModifiedDateStart() != null) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.lastModifiedDate.goe(teamParamDetailBO.getLastModifiedDateStart()));
        }
        if (teamParamDetailBO.getLastModifiedDateEnd() != null) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.lastModifiedDate.loe(teamParamDetailBO.getLastModifiedDateEnd()));
        }
        if (ConstantValidator.isAvaluableId(teamParamDetailBO.getCreatedBy())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.createdBy.eq(teamParamDetailBO.getCreatedBy()));
        }
        if (ConstantValidator.isAvaluableId(teamParamDetailBO.getLastModifiedBy())) {
            predicate = ExpressionUtils.and(predicate, _Q_Team.lastModifiedBy.eq(teamParamDetailBO.getLastModifiedBy()));
        }
        return predicate;
    }

    private void orderBy(Pageable pageable, QTeam _Q_Team, JPAQuery<Team> teamJPAQuery) {
        if (pageable.getSort() != null) {
            pageable.getSort().forEach(order -> {
                if (order != null && StringUtils.isNotEmpty(order.getProperty())) {
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
}
