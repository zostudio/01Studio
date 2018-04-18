package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.UserRole;

@Repository
public interface UserRoleRepository extends BaseJpaRepository<UserRole> {

}
