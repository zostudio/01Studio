package com.zos.security.rbac.dto.param;

import com.zos.security.rbac.support.enums.RoleType;
import lombok.Data;

@Data
public class RoleParamDTO {

    /**
     * 数据库表主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型
     */
    private RoleType roleType;

    /**
     * 角色描述
     */
    private String description;

}
