package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.TeamUserRelation;

@Repository
public interface TeamUserRelationRepository extends BaseJpaRepository<TeamUserRelation> {

}
