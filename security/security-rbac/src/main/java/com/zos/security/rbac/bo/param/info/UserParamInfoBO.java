package com.zos.security.rbac.bo.param.info;

import com.zos.security.rbac.bo.param.base.UserParamBaseBO;
import com.zos.security.rbac.support.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserParamInfoBO extends UserParamBaseBO {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 证件
     */
    private String identity;

    /**
     * 地址
     */
    private String address;

    /**
     * 生日
     */
    private String dateOfBirth;

    /**
     * 性别
     */
    private Gender gender;
}
