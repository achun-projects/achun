package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "创建文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryByFileCode implements Serializable {

    @Schema(description = "文件唯一标识")
    private String fileCode;

    @Schema(description = "是否包含删除文件")
    private Boolean containDeleted = false;
}
