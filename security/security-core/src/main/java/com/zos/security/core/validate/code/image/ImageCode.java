package com.zos.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.zos.security.core.validate.code.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 图片验证码
 *
 * @author 01Studio
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ImageCode extends ValidateCode {

	private static final long serialVersionUID = -6020470039852318468L;
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}

}
