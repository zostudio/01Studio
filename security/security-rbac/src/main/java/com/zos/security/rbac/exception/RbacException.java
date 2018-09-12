package com.zos.security.rbac.exception;

public abstract class RbacException extends RuntimeException {

    private Integer code;

    private String message;

    public RbacException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public RbacException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public RbacException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RbacException(String message) {
        super(message);
        this.message = message;
    }
}