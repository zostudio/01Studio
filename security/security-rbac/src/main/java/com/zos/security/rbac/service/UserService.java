package com.zos.security.rbac.service;

import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.bo.param.detail.UserParamDetailBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.bo.resopnse.info.UserInfoBO;
import com.zos.security.rbac.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	UserInfoBO create(UserInfoBO userInfoBO);

	UserInfoBO update(String id, UserInfoBO userInfoBO);

	void delete(String id);

	UserDetailBO getInfo(String id);

	Page<UserBaseBO> querySimple(UserParamBaseBO userParamBaseBO, Pageable pageable);

	Page<UserDetailBO> queryDetail(UserParamDetailBO userParamDetailBO, Pageable pageable);

    Boolean existByUsername(String username);

    Boolean existByEmail(String email);

    Boolean existByPhone(String phone);

    User signIn(String username);
}
