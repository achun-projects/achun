package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "根据dirCode查询文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryByDirCode implements Serializable {

    @Schema(description = "目录编码")
    private String dirCode;
}
