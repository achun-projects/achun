package site.achun.video.client.module.playlist.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/17 16:23
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemovePlaylist  implements Serializable {
    @Schema(title = "列表编码")
    private String plistCode;

    @Schema(title = "用户编码")
    private String userCode;

    @Schema(title = "暴力删除，记录也会被删除")
    private Boolean force;
}
