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
	public Object handleValidateResult(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult errors = (BindingResult)arg;
				if (errors.hasErrors()) {
					throw new ValidateException(errors.getAllErrors());
				}
			}
		}
		Object result = pjp.proceed();
		return result;
	}
}
