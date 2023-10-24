package site.achun.file.client.module.dir.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "根据Storage和路径")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ByStorageAndPath {
    private String storageCode;
    private String path;
}
