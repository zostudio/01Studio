package com.zos.security.rbac.web.handler;

import com.zos.security.rbac.validator.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 01Studio
 *
 */
@Slf4j
@ControllerAdvice
public class ValidateExceptionHandler {

	@ExceptionHandler(ValidateException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleUserNotExistException(ValidateException ex) {
		Map<String, Object> result = new HashMap<>();
		result.put("message", ex.getErrors());
		return result;
	}

}
