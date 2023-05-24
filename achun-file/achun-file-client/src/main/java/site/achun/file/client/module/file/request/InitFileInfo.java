package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "初始化文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InitFileInfo implements Serializable {

    private String unitCode;

    @Schema(name = "文件仓库标识")
    private String storageCode;

    private String absolutePath;

    @Schema(description = "文件名")
    private String fileName;
    private String md5;

    @Schema(description = "文件大小，以单位KB计算")
    private Integer size;

    @Schema(description = "文件类型, 0. 其他 1. 图片(jpg,jpeg,gif,png) 2. 视频(mp4,flv) 3. 音频(mp3)")
    private Integer type;

}