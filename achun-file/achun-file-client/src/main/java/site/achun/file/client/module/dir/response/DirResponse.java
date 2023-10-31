package site.achun.file.client.module.dir.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.storage.response.StorageResponse;

@Schema(description = "路径")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DirResponse{
    private String dirCode;
    private String parentCode;
    private String name;
    private String path;

    private String storageCode;
    private StorageResponse storage;
}
