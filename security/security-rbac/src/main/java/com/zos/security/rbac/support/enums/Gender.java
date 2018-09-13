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
 * 性别
 *
 * @author 01Studio
 */
@Getter
@AllArgsConstructor
public enum Gender implements BaseEnum, Serializable {

    /**
     * 男性
     */
    MALE("Male", "男性"),

    /**
     * 女性
     */
    FEMALE("Female", "女性");

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
    public Gender fromValue(String code) {
        for (Gender gender : Gender.values()) {
            if (String.valueOf(gender.getCode()).equals(code)) {
                return gender;
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
        for (Gender gender : Gender.values()) {
            valueList.add(gender.toString());
        }
        return valueList;
    }
}
