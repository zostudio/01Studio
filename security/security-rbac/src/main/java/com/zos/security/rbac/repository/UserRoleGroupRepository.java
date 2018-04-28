package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.UserRoleGroup;

@Repository
public interface UserRoleGroupRepository extends BaseJpaRepository<UserRoleGroup> {

}
