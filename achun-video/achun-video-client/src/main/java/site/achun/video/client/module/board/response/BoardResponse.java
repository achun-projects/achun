package site.achun.video.client.module.board.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/8 11:54
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse implements Serializable {

    private static final long serialVersionUID = -3757048315026016395L;

    private Integer id;
    /**
     * 相册编码
     */
    private String boardCode;

    /**
     * 画板名
     */
    private String name;

    /**
     * 画板描述
     */
    private String description;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名
     */
    private String groupNames;

    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 封面图，多个，用逗号分割
     */
    private String coverFileCodes;
    private String cover;

    private List<String> previews;

    /**
     * Banner图
     */
    private String bannerFileCode;

    /**
     * 是否为隐藏画板，1：隐藏，2：非隐藏
     */
    private Boolean hide;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    // 文件数量
    private Integer fileCount;
    // 最近更新时间, 易读的
    private LocalDateTime recordUtime;
}
