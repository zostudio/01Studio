package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.DepartmentUser;

@Repository
public interface DepartmentUserRepository extends BaseJpaRepository<DepartmentUser> {

}
