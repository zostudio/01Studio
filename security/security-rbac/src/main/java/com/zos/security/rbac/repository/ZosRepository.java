package com.zos.security.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author 01Studio
 *
 */
@NoRepositoryBean
public interface ZosRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
