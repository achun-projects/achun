package site.achun.video.client.module.channel.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/29 11:26
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelSimpleResponse implements Serializable {

    private static final long serialVersionUID = -6625669187708622265L;

    @Schema(title = "编码")
    private String channelCode;

    @Schema(title = "频道名")
    private String name;

    @Schema(title = "用户编码")
    private String userCode;

}
