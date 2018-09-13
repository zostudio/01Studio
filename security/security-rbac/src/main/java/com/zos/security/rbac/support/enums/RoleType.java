package com.zos.security.rbac.support.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.zos.security.rbac.support.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * 角色类型
 *
 * @author 01Studio
 */
@Getter
@AllArgsConstructor
public enum RoleType implements BaseEnum, Serializable {

    /**
     * 资源
     */
    RESOURCE("Resource", "资源"),

    /**
     * 流程
     */
    PROCESS("Process", "流程");

    private String code;
    private String note;

    @Override
    public String toString() {
        return String.valueOf(this.getCode());
    }

    @Override
    @JsonValue
    public String getCode() {
        return this.code;
    }

    /**
     * From code order status enum.
     *
     * @param code the code
     * @return the order status enum
     */
    @JsonCreator
    public RoleType fromValue(String code) {
        for (RoleType roleType : RoleType.values()) {
            if (String.valueOf(roleType.getCode()).equals(code)) {
                return roleType;
            }
        }
        return null;
    }

    /**
     * Gets the values list.
     *
     * @return valueList List
     */
    public static List<String> getValuesList() {
        List<String> valueList = Lists.newArrayList();
        for (RoleType roleType : RoleType.values()) {
            valueList.add(roleType.toString());
        }
        return valueList;
    }
}
