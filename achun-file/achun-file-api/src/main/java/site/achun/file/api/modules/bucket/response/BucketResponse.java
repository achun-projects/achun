package site.achun.file.api.modules.bucket.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "Bucket信息")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BucketResponse implements Serializable {

    @Schema(description = "bucket编码")
    private String bucketCode;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "创建时间")
    private LocalDateTime ctime;

    @Schema(description = "更新时间")
    private LocalDateTime utime;

}
