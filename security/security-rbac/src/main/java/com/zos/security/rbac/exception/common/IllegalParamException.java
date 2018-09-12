package com.zos.security.rbac.exception.common;

import com.zos.security.rbac.exception.RbacException;
import com.zos.security.rbac.support.constance.ResponseCode;

public class IllegalParamException extends RbacException {

    public IllegalParamException(Integer code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public IllegalParamException(Integer code, String message) {
        super(code, message);
    }

    public IllegalParamException(String message) {
        super(ResponseCode.INVALID, message);
    }
}
