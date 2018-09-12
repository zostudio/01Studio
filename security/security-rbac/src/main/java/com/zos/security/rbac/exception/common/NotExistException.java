package com.zos.security.rbac.exception.common;

import com.zos.security.rbac.exception.RbacException;
import com.zos.security.rbac.support.constance.ResponseCode;

public class NotExistException extends RbacException {

    public NotExistException(Integer code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public NotExistException(Integer code, String message) {
        super(code, message);
    }

    public NotExistException(String message) {
        super(ResponseCode.INVALID, message);
    }
}
