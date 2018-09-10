package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.UserRoleGroupRelation;

@Repository
public interface UserRoleGroupRelationRepository extends BaseJpaRepository<UserRoleGroupRelation> {

}
