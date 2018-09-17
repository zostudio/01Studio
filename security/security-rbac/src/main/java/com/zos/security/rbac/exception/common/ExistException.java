package com.zos.security.rbac.exception.common;

import com.zos.security.rbac.exception.RbacException;
import com.zos.security.rbac.support.constance.ResponseCode;

public class ExistException extends RbacException {

    public ExistException(Integer code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public ExistException(Integer code, String message) {
        super(code, message);
    }

    public ExistException(String message) {
        super(ResponseCode.INVALID, message);
    }
}
