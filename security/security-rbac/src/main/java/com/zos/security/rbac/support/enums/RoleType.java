package com.zos.security.rbac.support.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色类型
 *
 * @author 01Studio
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum RoleType implements Serializable {

    /**
     * 资源
     */
    RESOURCE("Resource"),

    /**
     * 流程
     */
    PROCESS("Process");

    private String roleType;

}
