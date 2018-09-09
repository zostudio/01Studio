package com.zos.security.rbac.validator.constraints;

import com.zos.security.rbac.validator.handler.NotAllowValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不接收任何值
 *
 * @author 01Studio
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotAllowValueValidator.class)
public @interface NotAllowValue {
	
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String field() default "不接收任何值";

}
