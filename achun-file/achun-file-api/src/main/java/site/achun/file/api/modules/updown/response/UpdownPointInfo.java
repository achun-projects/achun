package site.achun.file.api.modules.updown.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "存储信息")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdownPointInfo {

    @Schema(description = "服务节点地址")
    private String host;
}
