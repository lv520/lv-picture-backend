package com.lvhui.lvpicturebackend.manager.websocket;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.lvhui.lvpicturebackend.manager.auth.SpaceUserAuthManager;
import com.lvhui.lvpicturebackend.manager.auth.model.SpaceUserPermissionConstant;
import com.lvhui.lvpicturebackend.model.entity.Picture;
import com.lvhui.lvpicturebackend.model.entity.Space;
import com.lvhui.lvpicturebackend.model.entity.User;
import com.lvhui.lvpicturebackend.model.enums.SpaceTypeEnum;
import com.lvhui.lvpicturebackend.service.PictureService;
import com.lvhui.lvpicturebackend.service.SpaceService;
import com.lvhui.lvpicturebackend.service.UserService;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * websocket 拦截器 建立连接前校验
 */
@Component
@Slf4j
public class WsHandshakeInterceptor implements HandshakeInterceptor {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SpaceService spaceService;

    @Resource
    private SpaceUserAuthManager spaceUserAuthManager;

    /**
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes  会话属性设置
     * @return
     */
    @Override
    public boolean beforeHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response, @NotNull WebSocketHandler wsHandler, @NotNull Map<String, Object> attributes) {
        // 判断请求是否为 ServletServerHttpRequest 类型
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            // 获取请求参数
            String pictureId = servletRequest.getParameter("pictureId");
            // 判断 pictureId 是否为空
            if (StrUtil.isBlank(pictureId)) {
                log.error("缺少图片参数，拒绝握手");
                return false;
            }
            // 获取登录用户
            User loginUser = userService.getLoginUser(servletRequest);
            // 判断用户是否为空
            if (ObjUtil.isEmpty(loginUser)) {
                log.error("用户未登录，拒绝握手");
                return false;
            }
            // 校验用户是否有该图片的权限
            Picture picture = pictureService.getById(pictureId);
            // 判断图片是否存在
            if (picture == null) {
                log.error("图片不存在，拒绝握手");
                return false;
            }
            Long spaceId = picture.getSpaceId();
            Space space = null;
            // 判断图片是否属于某个空间
            if (spaceId != null) {
                space = spaceService.getById(spaceId);
                // 判断空间是否存在
                if (space == null) {
                    log.error("空间不存在，拒绝握手");
                    return false;
                }
                // 判断空间是否为团队空间
                if (space.getSpaceType() != SpaceTypeEnum.TEAM.getValue()) {
                    log.info("不是团队空间，拒绝握手");
                    return false;
                }
            }
            // 获取用户在该空间的权限列表
            List<String> permissionList = spaceUserAuthManager.getPermissionList(space, loginUser);
            // 判断用户是否有图片编辑权限
            if (!permissionList.contains(SpaceUserPermissionConstant.PICTURE_EDIT)) {
                log.error("没有图片编辑权限，拒绝握手");
                return false;
            }
            // 设置 attributes 设置用户属性到websocket会话中
            attributes.put("user", loginUser);
            attributes.put("userId", loginUser.getId());
            attributes.put("pictureId", Long.valueOf(pictureId)); // 记得转换为 Long 类型
        }
        return true;
    }

    @Override
    public void afterHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response, @NotNull WebSocketHandler wsHandler, Exception exception) {
    }
}

