package site.achun.video.client.module.playlist.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:12
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlayList implements Serializable {

    @Schema(title = "列表编码")
    private String plistCode;

    @Schema(title = "用户编码")
    private String userCode;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "描述")
    private String description;
}
