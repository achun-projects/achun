package site.achun.file.client.module.dir.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.file.response.FileInfoResponse;

@Schema(description = "文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private Boolean isDir;
    private String name;
    private FileInfoResponse fileInfo;
    private DirResponse dir;
}
