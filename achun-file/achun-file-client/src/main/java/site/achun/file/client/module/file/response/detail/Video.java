package site.achun.file.client.module.file.response.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Video extends Image{
    @Schema(description = "持续时长，视频音频等格式特有")
    private Integer duration;

}
