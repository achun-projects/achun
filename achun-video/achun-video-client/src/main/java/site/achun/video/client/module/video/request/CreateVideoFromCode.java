package site.achun.video.client.module.video.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.support.api.deserializer.ToEnumDeserializer;
import site.achun.video.client.constant.ViewLevelEnum;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVideoFromCode implements Serializable {

    private List<String> codes;

    // 1. 文件编码， 2. 目录编码
    @Schema(title = "类型")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private CreateVideoFromCodeType type;

    @Schema(title = "频道编码")
    private String channelCode;

    @Schema(title = "观看级别")
    @JsonDeserialize(using = ToEnumDeserializer.class)
    private ViewLevelEnum viewLevel;

    @Schema(title = "用户编码")
    private String userCode;

}
