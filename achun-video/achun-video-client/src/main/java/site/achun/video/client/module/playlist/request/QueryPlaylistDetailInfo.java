package site.achun.video.client.module.playlist.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/16 16:13
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryPlaylistDetailInfo implements Serializable {

    @Schema(title = "播放列表编码")
    @NotBlank(message = "播放列表编码不能为空")
    private String plistCode;

    @Schema(title = "用户编码")
    private String objectCode;
}
