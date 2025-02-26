package com.lvhui.lvpicturebackend.common;
import com.lvhui.lvpicturebackend.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局相应封装类   每个后端接口都要返回调用码、数据、调用信息等，前端可以根据这些信息进行相应的处理
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;
    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code,data,"");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
