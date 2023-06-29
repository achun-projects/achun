package site.achun.video.client.module.playlist.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:30
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayListRecord implements Serializable {

    @Schema(title = "列表编码")
    @NotBlank(message = "列表编码不能为空")
    private String plistCode;

    @Schema(title = "视频编码")
    @NotBlank(message = "视频编码不能为空")
    private String videoCode;

}
