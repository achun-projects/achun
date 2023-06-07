package site.achun.gallery.client.module.album.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/9 15:03
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumUpdateRequest implements Serializable {
    private static final long serialVersionUID = -3990501648315510702L;

    /**
     * 相册编码
     */
    private String albumCode;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 封面文件编码
     */
    private String coverFileCode;

    /**
     * 相册名
     */
    private String name;

    /**
     * 相册描述
     */
    private String description;
    /**
     * 来源
     */
    private String source;

    /**
     * Banner图文件编码
     */
    private String bannerFileCode;


    /**
     * 分组编码
     */
    private String groupCode;
}
