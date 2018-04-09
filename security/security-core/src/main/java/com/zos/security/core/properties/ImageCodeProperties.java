package com.zos.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片验证码配置项
 * 
 * @author 01Studio
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		// 覆盖父类默认值, 验证码默认显示 4 个字符
		setLength(4);
	}
	
	/**
	 * 图片宽
	 */
	private int width = 67;
	/**
	 * 图片高
	 */
	private int height = 23;

}
