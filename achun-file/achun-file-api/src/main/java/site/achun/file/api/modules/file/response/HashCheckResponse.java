package site.achun.file.api.modules.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.api.modules.bucket.response.BucketResponse;

import java.io.Serializable;

@Schema(description = "hash检测返回")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HashCheckResponse implements Serializable {

    @Schema(description = "Hash值")
    private String hash;

    @Schema(description = "bucket编码")
    private String bucket;

    @Schema(description = "bucket信息")
    private BucketResponse bucketResponse;

    @Schema(description = "是否存在")
    private Boolean exist;

    @Schema(description = "存在的文件")
    private Object existFile;

    @Schema(description = "updown服务节点host")
    private String updownHost;
}
