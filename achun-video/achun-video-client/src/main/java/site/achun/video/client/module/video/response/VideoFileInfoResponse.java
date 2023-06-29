package site.achun.video.client.module.video.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 * @Date: 2022/3/31 21:38
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoFileInfoResponse implements Serializable {
    private static final long serialVersionUID = 470170563451170621L;

    @Schema(title = "文件编码")
    private String fileCode;

    @Schema(title = "视频编码")
    private String videoCode;

    @Schema(title = "视频名称")
    private String videoName;

    @Schema(title = "访问地址")
    private String url;

    @Schema(title = "封面图片")
    private String cover;

    @Schema(title = "文件大小，以单位KB计算")
    private Integer size;

    @Schema(title = "宽度，图片视频等格式特有")
    private Integer width;

    @Schema(title = "高度，图片视频等格式特有")
    private Integer height;

    @Schema(title = "宽高比，乘100的整数")
    private Integer wh;

    @Schema(title = "持续时长，视频音频等格式特有")
    private Integer duration;

    @Schema(title = "创建时间")
    private LocalDateTime ctime;

    @Schema(title = "修改时间")
    private LocalDateTime utime;

    @Schema(title = "最后访问时间")
    private LocalDateTime atime;
}
