package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.RoleResourceRelation;

@Repository
public interface RoleResourceRelationRepository extends BaseJpaRepository<RoleResourceRelation> {

}
