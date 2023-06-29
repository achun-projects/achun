package site.achun.video.client.module.pictures.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/15 14:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePicture implements Serializable {
    private static final long serialVersionUID = -1858395689074374121L;

    /**
     * 文件唯一标识，系统生成，25年内不重复
     */
    private String fileCode;
    private String thirdId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 分组ID，当group相同时，视为一组资源。
     这个字段可以灵活使用。
     */
    private String setCode;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件大小，以单位KB计算
     */
    private Integer size;

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
     * 是否删除，1删除，2未删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    /**
     * 最后访问时间
     */
    private LocalDateTime atime;
}
