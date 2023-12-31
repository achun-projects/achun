package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建文件
 *
 * @Author: Heiffeng
 * @Date: 2021/12/1 0:29
 */
@Schema(description = "创建文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateFileInfo implements Serializable {
    private static final long serialVersionUID = 7376481450957049882L;

    @Schema(description = "文件唯一标识，系统生成，25年内不重复")
    private String fileCode;

    @Schema(description = "文件MD5码")
    private String md5;

    @Schema(description = "第三方唯一标识ID")
    private String thirdId;

    @Schema(description = "文件仓库标识")
    private String storageCode;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "分组ID，当group相同时，视为一组资源。")
    private String unitCode;

    @Schema(description = "文件类型, 0. 其他 1. 图片(jpg,jpeg,gif,png) 2. 视频(mp4,flv) 3. 音频(mp3)")
    private Integer type;

    @Schema(description = "文件后缀")
    private String suffix;

    @Schema(description = "在bucket内的存储路径")
    private String inStoragePath;

    @Schema(description = "封面图片，在bucket内的存储路径")
    private String cover;

    @Schema(description = "文件大小，以单位KB计算")
    private Integer size;

    @Schema(description = "宽度，图片视频等格式特有")
    private Integer width;

    @Schema(description = "高度，图片视频等格式特有")
    private Integer height;

    @Schema(description = "宽高比，乘100的整数")
    private Integer wh;

    @Schema(description = "持续时长，视频音频等格式特有")
    private Integer duration;

    @Schema(description = "文件源数据")
    private Object origin;
}
