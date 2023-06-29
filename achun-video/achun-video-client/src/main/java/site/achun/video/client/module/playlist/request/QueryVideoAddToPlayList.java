package site.achun.video.client.module.playlist.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.support.api.deserializer.ToEnumDeserializer;
import site.achun.video.client.constant.PlaylistTypeEnum;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/15 15:00
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryVideoAddToPlayList implements Serializable {


    @Schema(title = "视频编码")
    private String videoCode;
    @Schema(title = "模糊搜索")
    private String search;
    @Schema(title = "限制")
    private Integer limit = 100;
    @Schema(title = "用户编码")
    private String objectCode;

    @Schema(title = "播放列表类型")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private PlaylistTypeEnum objectType;
}
