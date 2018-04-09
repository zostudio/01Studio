package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.Admin;

/**
 * @author 01Studio
 *
 */
@Repository
public interface AdminRepository extends ZosRepository<Admin> {

	Admin findByUsername(String username);

}
