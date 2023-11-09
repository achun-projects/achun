package site.achun.gallery.client.module.album.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Schema(title = "创建或更新相册")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAlbum implements Serializable {

    @Schema(title = "相册编码")
    private String albumCode;

    @Schema(title = "相册名")
    private String name;

    @Schema(title = "相册描述")
    private String description;

    @Schema(title = "来源")
    private String source;

    @Schema(title = "分组编码")
    private String groupCode;

    @Schema(title = "封面文件编码")
    private String coverFileCode;

    @Schema(title = "用户编码")
    private String userCode;

    @Schema(title = "同步目录编码")
    private String dirCode;

}
