package site.achun.video.client.module.album.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/7 16:39
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse implements Serializable {
    private static final long serialVersionUID = -1642779737995064516L;

    private Integer id;
    /**
     * 相册编码
     */
    private String albumCode;

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
     * 用户编码
     */
    private String userCode;

    /**
     * 分组编码
     */
    private String groupCode;
    private List<String> groupCodes;

    /**
     * 分组名
     */
    private String groupNames;

    /**
     * 是否为隐藏相册，1：隐藏，2：非隐藏
     */
    private Boolean hide;

    /**
     * 相册封面，为fileCode
     */
    private String coverFileCode;
    private String cover;

    /**
     * Banner图
     */
    private String bannerFileCode;
    private String banner;

    // 文件数量
    private Integer fileCount;
    // 最近更新时间, 易读的
    private String recentAtime;
    /**
     * 分组总数
     */
    private Integer unitCount;
}
