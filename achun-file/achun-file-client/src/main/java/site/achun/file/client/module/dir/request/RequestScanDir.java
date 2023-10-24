package site.achun.file.client.module.dir.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "请求扫描目录")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestScanDir {
    private String dirCode;
}
