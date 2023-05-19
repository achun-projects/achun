package site.achun.file.client.module.bucket.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "Bucket")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BucketResponse implements Serializable {

    @Schema(name = "bucket编码")
    private String bucketCode;

    @Schema(name = "名称")
    private String name;

    @Schema(name = "创建时间")
    private LocalDateTime ctime;

    @Schema(name = "更新时间")
    private LocalDateTime utime;

}
