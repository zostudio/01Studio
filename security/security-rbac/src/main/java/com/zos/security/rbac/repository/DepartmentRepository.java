package com.zos.security.rbac.repository;

import org.springframework.stereotype.Repository;

import com.zos.security.rbac.domain.Department;

@Repository
public interface DepartmentRepository extends BaseJpaRepository<Department> {

}
