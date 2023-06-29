package site.achun.video.client.module.channel.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/11 18:13
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChannel implements Serializable {

    @Schema(title = "频道编码")
    @NotBlank(message = "频道编码不能为空")
    private String channelCode;

    @Schema(title = "频道名")
    private String name;

    @Schema(title = "用户编码")
    private String userCode;

    @Schema(title = "封面文件编码")
    private String coverFileCode;

    @Schema(title = "横幅文件编码")
    private String bannerFileCode;
}
