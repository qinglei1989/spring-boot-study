package com.rrc.enums;

public enum ResultEnum {
    RESULT_REQ_SUCC("0000", "请求成功"),
    RESULT_REQ_FAIL("1001", "系统繁忙,请稍后重试");

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
