package site.achun.video.client.module.playlist.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.support.api.deserializer.ToEnumDeserializer;
import site.achun.video.client.constant.PlaylistTypeEnum;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/12/9 15:13
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListPreviewResponse implements Serializable {


    @Schema(title = "列表编码")
    private String plistCode;

    @Schema(title = "用户编码")
    private String objectCode;

    @Schema(title = "播放列表类型")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private PlaylistTypeEnum objectType;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "创建时间")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ctime;

    @Schema(title = "更新时间")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime utime;

    @Schema(title = "记录更新时间")
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ruTime;

    @Schema(title = "视频数量")
    private Long recordCount;

    @Schema(title = "预览视频数据")
    private List<VideoInfoResponse> previewVideos;

}
