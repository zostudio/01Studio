package com.zos.security.rbac.dto;

import com.zos.security.rbac.support.RoleType;

public class RoleConditionDTO {

    /**
     * 数据库表主键
     */
    private Long id;

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
