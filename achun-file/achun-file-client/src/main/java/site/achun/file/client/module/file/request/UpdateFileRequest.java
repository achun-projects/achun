package site.achun.file.client.module.file.request;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateFileRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileCode;

    /**
     * 1. 隐藏文件，2. 非隐藏文件
     */
    private Integer hidden;

    /**
     * 是否删除，1删除，2未删除
     */
    private Integer deleted;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 宽度，图片视频等格式特有
     */
    private Integer width;

    /**
     * 高度，图片视频等格式特有
     */
    private Integer height;

    /**
     * 宽高比，乘100的整数

     */
    private Integer wh;

    /**
     * 持续时长，视频音频等格式特有
     */
    private Integer duration;

    /**
     * 封面
     */
    private String cover;

    // 网图
    private String mediumUrl;
    // 缩略图
    private String smallUrl;
}
