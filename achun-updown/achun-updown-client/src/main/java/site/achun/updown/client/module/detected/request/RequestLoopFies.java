package site.achun.updown.client.module.detected.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "请求遍历文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestLoopFies implements Serializable {

    @Schema(description = "本地绝对路径")
    private String localPath;

    private String storageCode;
}
