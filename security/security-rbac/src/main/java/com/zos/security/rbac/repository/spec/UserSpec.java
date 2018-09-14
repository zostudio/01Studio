package com.zos.security.rbac.repository.spec;

import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.repository.support.ZosSpecification;
import com.zos.security.rbac.repository.support.QueryWraper;

/**
 * @author 01Studio
 *
 */
public class UserSpec extends ZosSpecification<User, UserParamBaseBO> {

	public UserSpec(UserParamBaseBO userParamBaseBO) {
		super(userParamBaseBO);
	}

	@Override
	protected void addCondition(QueryWraper<User> queryWraper) {
		//addLikeCondition(queryWraper, "username");
	}

}
