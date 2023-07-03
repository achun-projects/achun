package site.achun.gallery.client.module.pic_unit.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateUnit implements Serializable {

    @Schema(title = "用户code")
    private String userCode;

    @Schema(title = "分组名称")
    private String name;

    @Schema(title = "描述")
    private String desc;

    @Schema(title = "单元编码")
    private String unitCode;

    @Schema(title = "标签组")
    private Collection<String> tags;

}
