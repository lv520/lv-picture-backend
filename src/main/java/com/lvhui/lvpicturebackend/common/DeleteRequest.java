package com.lvhui.lvpicturebackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求类 删除请求包装类，接受要删除数据的 id 作为参数
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
