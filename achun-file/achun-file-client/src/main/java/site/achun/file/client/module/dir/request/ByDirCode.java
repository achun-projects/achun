package site.achun.file.client.module.dir.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "根据路径编码")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ByDirCode {

    @Schema(description = "目录编码")
    private String dirCode;

}
