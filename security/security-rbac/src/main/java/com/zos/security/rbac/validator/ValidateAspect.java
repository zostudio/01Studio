package com.zos.security.rbac.validator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author 01Studio
 *
 */
@Slf4j
@Aspect
@Component
public class ValidateAspect {
	
	@Around("execution(* com.zos.security.rbac.web.controller.*.*.*(..))")
	public Object handleValidateResult(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] arguments = proceedingJoinPoint.getArgs();
		for (Object argument : arguments) {
			if(argument instanceof BindingResult) {
				if (((BindingResult) argument).hasErrors()) {
					throw new ValidateException(((BindingResult) argument).getAllErrors());
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
