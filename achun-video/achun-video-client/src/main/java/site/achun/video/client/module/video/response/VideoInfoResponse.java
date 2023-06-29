package site.achun.video.client.module.video.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.video.client.constant.ViewLevelEnum;
import site.achun.video.client.module.channel.response.ChannelResponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/28 17:22
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoInfoResponse implements Serializable {
    private static final long serialVersionUID = 179677939137977648L;

    @Schema(title = "视频编码")
    private String videoCode;

    @Schema(title = "频道")
    private ChannelResponse channelResponse;

    @Schema(title = "视频封面")
    private String coverFileUrl;

    @Schema(title = "用户code")
    private String userCode;

    @Schema(title = "人物code")
    private String personCode;

    @Schema(title = "视频标题")
    private String title;

    @Schema(title = "视频简介")
    private String description;

    @Schema(title = "创作者")
    private String author;

    /**
     * 1. 正常，所有年龄段均可观看
     2. 暴露，NSFW
     3. 限制级，R18
     4. 夸张，轻微SM
     5. 引起不适，SM，重口

     */
    @Schema(title = "view_level")
    private ViewLevelEnum viewLevel;

    @Schema(title = "来源类型，0.自制，1.转载")
    private Integer sourceType;

    @Schema(title = "转载地址")
    private String sourceUrl;

    @Schema(title = "创建时间")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ctime;

    @Schema(title = "格式化创建时间")
    private String ctimeFormat;

    @Schema(title = "视频点击量")
    private Integer clickNum;

    @Schema(title = "标签")
    private Collection<String> tags;

    @Schema(title = "默认视频")
    private VideoFileInfoResponse defaultVideoFileInfo;

    @Schema(title = "所有视频")
    private Collection<VideoFileInfoResponse> videoFileInfoList;
}
