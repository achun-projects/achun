package site.achun.file.client.module.storage.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.bucket.response.BucketResponse;

import java.io.Serializable;
import java.time.LocalDateTime;


@Schema(description = "存储节点信息")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageResponse implements Serializable {

    @Schema(name = "存储名")
    private String name;

    @Schema(name = "存储编码")
    private String storageCode;

    @Schema(name = "所属bucket")
    private String bucketCode;

    @Schema(name = "所属bucket")
    private BucketResponse bucket;

    @Schema(name = "路径")
    private String path;

    @Schema(name = "访问前缀")
    private String accessPrefix;

    @Schema(name = "1. 正常，2.禁用")
    private Integer status;

    @Schema(name = "额外信息")
    private Object extra;

    @Schema(name = "创建时间")
    private LocalDateTime ctime;

    @Schema(name = "修改时间")
    private LocalDateTime utime;

}
