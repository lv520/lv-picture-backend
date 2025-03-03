package com.lvhui.lvpicturebackend.api.imagesearch.baidu.model;

import lombok.Data;

/**
 * 以图搜图 图片搜索结果
 */
@Data
public class ImageSearchResult {

    /**
     * 缩略图地址
     */
    private String thumbUrl;

    /**
     * 来源地址
     */
    private String fromUrl;
}
