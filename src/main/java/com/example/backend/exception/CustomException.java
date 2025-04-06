package com.example.backend.exception;


/**
 * 自定义异常的基类
 */
public class CustomException extends RuntimeException {
    private final int errorCode; // 错误码

    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
