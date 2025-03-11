package com.lvhui.lvpicturebackend.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvhui.lvpicturebackend.exception.BusinessException;
import com.lvhui.lvpicturebackend.exception.ErrorCode;
import com.lvhui.lvpicturebackend.model.dto.user.UserQueryRequest;
import com.lvhui.lvpicturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvhui.lvpicturebackend.model.enums.UserRoleEnum;
import com.lvhui.lvpicturebackend.model.vo.LoginUserVO;
import com.lvhui.lvpicturebackend.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 17684
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-02-25 13:37:47
*/

public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request   需要session对象
     * @return 脱敏后的用户信息  有的时候前端不要要返回全部对象 比如密码
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取加密后的密码
     * @param userPassword
     * @return
     */

    String getEncryptPassword(String userPassword);

    /**
     * 获取当前登录用户
     *  系统内部用
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);


    /**
     *  获得脱敏后的登录用户信息
     * @param user
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     *  获得脱敏后的用户信息
     * @param user
     * @return
     */

    UserVO getUserVO(User user) ;


    /**
     * 获得脱敏后的用户信息列表
     * @param userList
     * @return
     */


    List<UserVO> getUserVOList(List<User> userList);


    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取查询条件
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * 用户兑换会员
     */
    boolean exchangeVip(User user, String vipCode);
}
