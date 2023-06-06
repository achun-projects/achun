package site.achun.file.client.module.download.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "下载结果")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    @Schema(title = "文件编码")
    private String fileCode;

    @Schema(title = "分组编码")
    private String unit;

    @Schema(title = "Bucket编码")
    private String storage;

    @Schema(title = "冗余字段，Bucket本地路径")
    private String storageLocalDir;

    @Schema(title = "文件在Bucket中的保存路径")
    private String filePath;

    @Schema(title = "下载链接")
    private String url;

    @Schema(title = "本地存在时是否覆盖，1.覆盖。2.不覆盖")
    private Boolean override;

    @Schema(title = "下载耗时，单位：ms")
    private Integer costTime;

    @Schema(title = "文件大小")
    private Long fileSize;

    @Schema(title = "下载状态")
    private Integer status;

    @Schema(title = "状态码")
    private String mark;

    @Schema(title = "http下载相应状态码")
    private Integer httpStatus;

    @Schema(title = "下载失败原因说明")
    private String description;

    @Schema(title = "本地是否存在文件，1.存在，2.不存在")
    private Boolean existsLocalFile;

}
