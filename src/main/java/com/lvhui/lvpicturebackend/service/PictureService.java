package com.lvhui.lvpicturebackend.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvhui.lvpicturebackend.model.dto.picture.PictureQueryRequest;
import com.lvhui.lvpicturebackend.model.dto.picture.PictureUploadRequest;
import com.lvhui.lvpicturebackend.model.dto.user.UserQueryRequest;
import com.lvhui.lvpicturebackend.model.entity.Picture;
import com.lvhui.lvpicturebackend.model.entity.User;
import com.lvhui.lvpicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 17684
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-02-27 14:01:50
*/
public interface PictureService extends IService<Picture> {
    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);


    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);


    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 获取图片包装类 分页
     * @param picturePage
     * @param request
     * @return
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 校验图片
     * @param picture
     */
    void validPicture(Picture picture);
}
