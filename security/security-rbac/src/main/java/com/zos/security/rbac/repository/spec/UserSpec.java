package com.zos.security.rbac.repository.spec;

import com.zos.security.rbac.bo.UserConditionBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.repository.support.ZosSpecification;
import com.zos.security.rbac.repository.support.QueryWraper;

/**
 * @author 01Studio
 *
 */
public class UserSpec extends ZosSpecification<User, UserConditionBO> {

	public UserSpec(UserConditionBO userConditionBO) {
		super(userConditionBO);
	}

	@Override
	protected void addCondition(QueryWraper<User> queryWraper) {
		//addLikeCondition(queryWraper, "username");
	}

}
