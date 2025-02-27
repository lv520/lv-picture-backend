package com.lvhui.lvpicturebackend.model.vo;

import cn.hutool.json.JSONUtil;
import com.lvhui.lvpicturebackend.model.entity.Picture;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 图片标签列表视图
 */
@Data
public class PictureTagCategory {
    /**
     * 标签列表
     */
    private List<String> tagList;
    /**
     * 分类列表
     */
    private List<String> categoryList;

}
