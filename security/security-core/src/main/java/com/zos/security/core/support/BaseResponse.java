package com.zos.security.core.support;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 简单响应
 * 
 * @author 01Studio
 *
 */
@Data
@AllArgsConstructor
public class BaseResponse {

	/**
	 * 响应体
	 */
	private Object content;

}
