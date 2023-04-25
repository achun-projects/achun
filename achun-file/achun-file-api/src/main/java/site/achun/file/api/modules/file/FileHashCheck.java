package site.achun.file.api.modules.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "Bucket信息")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileHashCheck implements Serializable {

    @Schema(description = "Hash值")
    private String hash;

    @Schema(description = "bucket编码")
    private String bucket;
}
