package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.UserRoleRelation;

@Repository
public interface UserRoleRelationRepository extends BaseJpaRepository<UserRoleRelation> {

}
