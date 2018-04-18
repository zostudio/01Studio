package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.Resource;

@Repository
public interface ResourceRepository extends BaseJpaRepository<Resource> {

}
