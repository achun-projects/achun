package site.achun.file.api.modules.storage.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.api.modules.bucket.response.BucketResponse;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageResponse implements Serializable {

    @Schema(description = "存储名")
    private String name;

    @Schema(description = "存储编码")
    private String storageCode;

    @Schema(description = "所属bucketCode")
    private String bucketCode;
    @Schema(description = "所属bucket")
    private BucketResponse bucket;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "访问前缀")
    private String accessPrefix;

    @Schema(description = "1. 正常，2.禁用")
    private Integer status;
}
