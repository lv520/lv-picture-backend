package com.lvhui.lvpicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/*
*  用户d登录请求
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 4058094784193278746L;
    /*
    账号
     */
    private String userAccount;
    /*
    密码
     */
    private String userPassword;

}
