package site.achun.gallery.client.module.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateBoard implements Serializable {

    @Schema(title = "相册编码")
    private String boardCode;

    @Schema(title = "用户编码")
    private String userCode;

    @Schema(title = "画板名")
    private String name;

    @Schema(title = "画板描述")
    private String description;

    @Schema(title = "分组编码集合")
    private String groupCode;

    @Schema(title = "是否为隐藏画板，1：隐藏，2：非隐藏")
    private Boolean hide;

    @Schema(title = "封面文件编码")
    private String cover;
}
