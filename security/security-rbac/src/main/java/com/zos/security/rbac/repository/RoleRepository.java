package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.Role;

@Repository
public interface RoleRepository extends BaseJpaRepository<Role> {

}
