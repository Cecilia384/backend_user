package com.example.backend.exception;


/**
 * 认证或授权异常
 */
public class AuthenticationException extends CustomException {
    public AuthenticationException(String message) {
        super(4003, message); // 错误码 4003 表示认证异常
    }
}
