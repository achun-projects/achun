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
 * @Date: 2022/11/10 18:11
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayList implements Serializable {

    @Schema(title = "对象编码")
    private String objectCode;

    @Schema(title = "播放列表类型")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private PlaylistTypeEnum objectType;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "描述")
    private String description;

}
