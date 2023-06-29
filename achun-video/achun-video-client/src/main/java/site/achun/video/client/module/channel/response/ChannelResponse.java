package site.achun.video.client.module.channel.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/2 11:27
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelResponse implements Serializable {
    private static final long serialVersionUID = 3030244412101526991L;

    @Schema(title = "频道编码")
    private String channelCode;
    @Schema(title = "频道名")
    private String name;
    @Schema(title = "用户编码")
    private String userCode;
    @Schema(title = "封面")
    private String coverFileCode;
    @Schema(title = "头像图片地址")
    private String coverFileUrl;
    @Schema(title = "横幅")
    private String bannerFileCode;
    @Schema(title = "Banner图地址")
    private String bannerFileUrl;
    @Schema(title = "创建时间")
    private LocalDateTime ctime;
    @Schema(title = "更新时间")
    private LocalDateTime utime;
    @Schema(title = "访问时间")
    private LocalDateTime atime;
}
