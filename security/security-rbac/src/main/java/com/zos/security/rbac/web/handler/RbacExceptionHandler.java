package com.zos.security.rbac.web.handler;

import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.exception.RbacException;
import com.zos.security.rbac.support.constance.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author 01Studio
 *
 */
@Slf4j
@ControllerAdvice
public class RbacExceptionHandler {

    @ExceptionHandler(RbacException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseDTO<RbacException> handleRbacException(RbacException ex) {
        ResponseDTO<RbacException> result = new ResponseDTO<RbacException>();
        result.setCode(ResponseCode.INVALID);
        result.setData(ex);
        result.setMessage(ex.getMessage());
		return result;
	}

}
