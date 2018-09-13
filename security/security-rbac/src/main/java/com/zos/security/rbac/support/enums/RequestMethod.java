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
 * 请求方式: GET, POST, PUT, DELETE, ALL
 * 
 * @author 01Studio
 *
 */
@Getter
@AllArgsConstructor
public enum RequestMethod implements BaseEnum, Serializable {

	/**
	 * 查询
	 */
	GET("Get", "GET"),
	
	/**
	 * 新增
	 */
	POST("Post", "POST"),
	
	/**
	 * 更新
	 */
	PUT("Put", "PUT"),

	/**
	 * 匹配更新
	 */
	PATCH("Patch", "PATCH"),
	
	/**
	 * 删除
	 */
	DELETE("Delete", "DELETE"),
	
	/**
	 * 所有类型
	 */
	ALL("All", "ALL");

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
	public RequestMethod fromValue(String code) {
		for (RequestMethod requestMethod : RequestMethod.values()) {
			if (String.valueOf(requestMethod.getCode()).equals(code)) {
				return requestMethod;
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
		for (RequestMethod requestMethod : RequestMethod.values()) {
			valueList.add(requestMethod.toString());
		}
		return valueList;
	}
}
