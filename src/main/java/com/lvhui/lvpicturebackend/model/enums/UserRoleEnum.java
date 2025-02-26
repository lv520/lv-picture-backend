package com.lvhui.lvpicturebackend.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/*
用户角色枚举 两种类型
 */
@Getter
public enum UserRoleEnum {
    USER("用户","User"),
    ADMIN("管理员","admin");
    private final String text;
    private final String value;
    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }
    //根据user 返回枚举类  根据值获取枚举
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum e : UserRoleEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;

    }
}
