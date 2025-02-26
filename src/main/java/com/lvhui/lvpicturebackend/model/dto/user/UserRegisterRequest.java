package com.lvhui.lvpicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/*
*  用户注册请求
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 4058094784193278746L;
    /*
    账号
     */
    private String userAccount;
    /*
    密码
     */
    private String userPassword;
    /*
    确认密码
     */
    private String checkPassword;
}
