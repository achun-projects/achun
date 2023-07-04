package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "根据Md5查询文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryByMD5 {

    @Schema(description = "MD5")
    private String md5;
}
