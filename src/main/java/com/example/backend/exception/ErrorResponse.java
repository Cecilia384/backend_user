package com.example.backend.exception;

/**
 * 错误响应类，用于返回统一的错误格式
 */
public class ErrorResponse {
    private int code; // 错误码
    private String message; // 错误信息

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
