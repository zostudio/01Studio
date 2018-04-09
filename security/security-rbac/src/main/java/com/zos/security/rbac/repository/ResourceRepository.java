package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.Resource;

/**
 * @author 01Studio
 *
 */
@Repository
public interface ResourceRepository extends ZosRepository<Resource> {

	Resource findByName(String name);

}
