package site.achun.file.client.module.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.storage.response.StorageResponse;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "初始化文件返回")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InitFileInfoResponse extends InitFileInfo implements Serializable {

    @Schema(name = "文件编码")
    private String fileCode;

    @Schema(name = "在bucket内的存储路径")
    private String inStoragePath;

    @Schema(description = "文件封面")
    private String cover;

    @Schema(description = "修改时间")
    private LocalDateTime utime;

    @Schema(name = "文件仓库")
    private StorageResponse storage;

    @Schema(name = "是否存在")
    private Boolean exist;

}
