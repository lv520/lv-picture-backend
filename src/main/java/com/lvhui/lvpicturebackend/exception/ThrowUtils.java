package com.lvhui.lvpicturebackend.exception;

/**
 *  异常处理工具类（断言类，简化抛异常的代码）
 */
public class ThrowUtils {
    /**
     * 条件成立及异常
     * @param condition        条件
     * @param runtimeException 异常
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }
    /**
     * 条件成立及异常
     * @param condition        条件
     * @param errorCode        异常
     */

    public  static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition,new BusinessException(errorCode));
    }
    /**
     * 条件成立及异常
     * @param condition        条件
     * @param errorCode        异常
     * @param message          错误消息
     */

    public  static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition,new BusinessException(errorCode,message));
    }

}
