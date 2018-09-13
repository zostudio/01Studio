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
 * 资源类型: INTERFACE, MENU, BUTTON, OTHER
 * 
 * @author 01Studio
 *
 */
@Getter
@AllArgsConstructor
public enum ResourceType implements BaseEnum, Serializable {

	/**
	 * 接口
	 */
	INTERFACE("Interface", "接口"),

	/**
	 * 菜单
	 */
	MENU("Menu", "菜单"),
	
	/**
	 * 按钮
	 */
	BUTTON("Button", "按钮"),
	
	/**
	 * 其他
	 */
	OTHER("Other", "其他");

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
    public ResourceType fromValue(String code) {
        for (ResourceType resourceType : ResourceType.values()) {
            if (String.valueOf(resourceType.getCode()).equals(code)) {
                return resourceType;
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
        for (ResourceType resourceType : ResourceType.values()) {
            valueList.add(resourceType.toString());
        }
        return valueList;
    }
}
