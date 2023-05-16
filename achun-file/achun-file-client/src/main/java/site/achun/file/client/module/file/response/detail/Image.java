package site.achun.file.client.module.file.response.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Image extends Detail{

    @Schema(description = "宽度，图片视频等格式特有")
    private Integer width;

    @Schema(description = "高度，图片视频等格式特有")
    private Integer height;

    @Schema(description = "宽高比，乘100的整数")
    private Integer wh;

}
