package site.achun.file.client.module.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "文件信息返回实体")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFileResponse implements Serializable {

    @Schema(description = "文件唯一标识，系统生成，25年内不重复")
    private String fileCode;

    @Schema(description = "第三方唯一标识ID")
    private String thirdId;

    @Schema(description = "分组ID，当group相同时，视为一组资源。")
    private String unitCode;

    @Schema(description = "Bucket编码")
    private String bucketCode;

    @Schema(description = "文件名")
    private String fileName;
    @Schema(description = "后缀名")
    private String suffix;

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

    @Schema(description = "宽度，图片视频等格式特有")
    private Integer width;

    @Schema(description = "高度，图片视频等格式特有")
    private Integer height;

    @Schema(description = "宽高比，乘100的整数")
    private Integer wh;

    @Schema(description = "持续时长，视频音频等格式特有")
    private Integer duration;

}
