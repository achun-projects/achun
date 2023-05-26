package site.achun.file.client.module.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.file.response.detail.Detail;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "文件信息返回实体")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoResponse<T extends Detail> implements Serializable {

    @Schema(description = "文件唯一标识，系统生成，25年内不重复")
    private String fileCode;

    @Schema(description = "第三方唯一标识ID")
    private String thirdId;

    @Schema(description = "分组ID，当group相同时，视为一组资源。")
    private String unitCode;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "文件类型, 0. 其他 1. 图片(jpg,jpeg,gif,png) 2. 视频(mp4,flv) 3. 音频(mp3)")
    private Integer type;

    @Schema(description = "文件封面")
    private String cover;

    @Schema(description = "文件大小，以单位KB计算")
    private Long size;

    @Schema(description = "修改时间")
    private LocalDateTime utime;

    @Schema(description = "文件访问url")
    private String url;

    @Schema(description = "网图访问url")
    private String mediumUrl;

    @Schema(description = "详细信息")
    private T detail;
}
