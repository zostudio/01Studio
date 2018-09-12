package com.zos.security.rbac.support.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 资源类型: INTERFACE, MENU, BUTTON, OTHER
 * 
 * @author 01Studio
 *
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum ResourceType implements Serializable {

	/**
	 * 接口
	 */
	INTERFACE("Interface"),

	/**
	 * 菜单
	 */
	MENU("Menu"),
	
	/**
	 * 按钮
	 */
	BUTTON("Button"),
	
	/**
	 * 其他
	 */
	OTHER("Other");

	private String resourcesType;
}
