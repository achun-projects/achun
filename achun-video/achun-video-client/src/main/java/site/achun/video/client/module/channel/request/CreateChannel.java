package site.achun.video.client.module.channel.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/11 18:07
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannel implements Serializable {

    @Schema(title = "频道名")
    private String name;

    @Schema(title = "用户编码")
    private String userCode;

    @Schema(title = "封面文件编码")
    private String coverFileCode;

    @Schema(title = "横幅文件编码")
    private String bannerFileCode;

}
