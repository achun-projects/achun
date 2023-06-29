package site.achun.video.client.module.video.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.deserializer.ToEnumDeserializer;
import site.achun.video.client.constant.ViewLevelEnum;

import java.io.Serializable;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/31 20:52
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateVideoRequest implements Serializable {

    private static final long serialVersionUID = 3930370443960731596L;

    @Schema(title = "视频编码")
    private String videoCode;

    @Schema(title = "频道编码")
    private String channelCode;

    @Schema(title = "封面文件编码")
    private String coverFileCode;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "简介")
    private String description;

    @Schema(title = "来源类型，0.自制，1.转载")
    private Integer sourceType;
    @Schema(title = "来源类型地址")
    private String sourceUrl;

    @Schema(title = "观看级别")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private ViewLevelEnum viewLevel;

    private Collection<String> tags;

    private Collection<MediaFileResponse> videoFiles;

    private String userCode;

    private String plistCode;

}
