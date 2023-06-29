package site.achun.video.client.module.playlist.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.achun.support.api.deserializer.ToEnumDeserializer;
import site.achun.support.api.request.ReqPage;
import site.achun.video.client.constant.PlaylistTypeEnum;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:12
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryPlayListPageRequest extends ReqPage implements Serializable {

    @Schema(title = "名称")
    private String likeName;

    @Schema(title = "对象编码")
    private String objectCode;

    @Schema(title = "播放列表类型")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private PlaylistTypeEnum objectType;

}
