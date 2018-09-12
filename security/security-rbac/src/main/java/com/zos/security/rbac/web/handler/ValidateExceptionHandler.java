package com.zos.security.rbac.web.handler;

import com.google.common.collect.Lists;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.support.constance.ResponseCode;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.validator.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 01Studio
 *
 */
@Slf4j
@ControllerAdvice
public class ValidateExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException.class)
	public @ResponseBody ResponseDTO<List<ObjectError>> handleValidateException(ValidateException ex) {
        ResponseDTO<List<ObjectError>> result = new ResponseDTO<List<ObjectError>>();
        result.setCode(ResponseCode.INVALID);
        result.setData(ex.getErrors());
		return result;
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseDTO<List<ObjectError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ResponseDTO<List<ObjectError>> result = new ResponseDTO<List<ObjectError>>();
        result.setCode(ResponseCode.INVALID);
        if (ConstantValidator.isNotNull(ex.getBindingResult()) && ex.getBindingResult().hasErrors()) {
            result.setData(ex.getBindingResult().getAllErrors());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < ex.getBindingResult().getAllErrors().size(); i++) {
                stringBuffer.append(ex.getBindingResult().getAllErrors().get(i).getDefaultMessage());
                if (ex.getBindingResult().getAllErrors().size() > 1 && (i + 1) < ex.getBindingResult().getAllErrors().size()) {
                    stringBuffer.append(", ");
                }
            }
            result.setMessage(stringBuffer.toString());
        } else {
            ObjectError objectError = new ObjectError(ex.getClass().getSimpleName(), ex.getMessage());
            result.setData(Lists.newArrayList(objectError));
            result.setMessage(ex.getMessage());
        }
        return result;
    }

}
