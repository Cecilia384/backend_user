package com.example.backend.exception;


/**
 * 数据校验异常
 */
public class ValidationException extends CustomException {
    public ValidationException(String message) {
        super(4002, message); // 错误码 4002 表示校验异常
    }
}
