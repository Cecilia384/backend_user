package com.example.backend.exception;


/**
 * 业务逻辑异常
 */
public class BusinessException extends CustomException {
    public BusinessException(String message) {
        super(4001, message); // 错误码 4001 表示业务逻辑异常
    }
}
