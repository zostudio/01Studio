package com.zos.security.rbac.support;

import lombok.*;

import java.io.Serializable;

/**
 * 请求方式: GET, POST, PUT, DELETE, ALL
 * 
 * @author 01Studio
 *
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum RequestMethod implements Serializable {

	/**
	 * 查询
	 */
	GET("Get"),
	
	/**
	 * 新增
	 */
	POST("Post"),
	
	/**
	 * 更新
	 */
	PUT("Put"),

	/**
	 * 匹配更新
	 */
	PATCH("Patch"),
	
	/**
	 * 删除
	 */
	DELETE("Delete"),
	
	/**
	 * 所有类型
	 */
	ALL("All");

	private String requestMethod;

}
