package com.lvhui.lvpicturebackend.exception;

import lombok.Getter;

/**
 * 自定义异常 和 内置的异常类区分开  便于定制化输出错误信息
 */
@Getter
public class BusinessException extends RuntimeException{

    private final int code;
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
