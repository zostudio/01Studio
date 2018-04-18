package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.User;

@Repository
public interface UserRepository extends BaseJpaRepository<User> {

}
