package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Schema(description = "根据dirCodes查询文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryByDirCodes implements Serializable {

    @Schema(description = "目录编码")
    private List<String> dirCodes;
}
