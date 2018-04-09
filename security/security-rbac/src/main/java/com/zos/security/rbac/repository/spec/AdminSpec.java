package com.zos.security.rbac.repository.spec;

import com.zos.security.rbac.domain.Admin;
import com.zos.security.rbac.dto.AdminCondition;
import com.zos.security.rbac.repository.support.ZosSpecification;
import com.zos.security.rbac.repository.support.QueryWraper;

/**
 * @author 01Studio
 *
 */
public class AdminSpec extends ZosSpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
