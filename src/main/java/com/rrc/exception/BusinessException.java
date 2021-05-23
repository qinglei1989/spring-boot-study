package com.rrc.exception;

public class BusinessException extends RuntimeException{

    private String errCode;

    public BusinessException() {
        super();
    }

    public BusinessException(String errCode) {
        this.errCode = errCode;
    }

    public BusinessException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public BusinessException(String message, Throwable cause, String errCode) {
        super(message, cause);
        this.errCode = errCode;

    }

    public String getErrCode() {
        return errCode;
    }

}
