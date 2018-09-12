package com.zos.security.rbac.validator.handler;

import com.zos.security.rbac.validator.constraints.NotAllowValue;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 不接收任何值
 *
 * @author 01Studio
 */
@Slf4j
public class NotAllowValueValidator implements ConstraintValidator<NotAllowValue, Object> {
	
	@Override
	public void initialize(NotAllowValue constraintAnnotation) {
		log.info("NotAllowValueValidator Initialize");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else {
			return false;
		}
	}

}
