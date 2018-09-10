package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.DepartmentUserRelation;

@Repository
public interface DepartmentUserRelationRepository extends BaseJpaRepository<DepartmentUserRelation> {

}
