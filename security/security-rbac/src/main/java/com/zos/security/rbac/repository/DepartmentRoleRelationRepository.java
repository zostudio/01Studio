package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.DepartmentRoleRelation;

@Repository
public interface DepartmentRoleRelationRepository extends BaseJpaRepository<DepartmentRoleRelation> {

}
