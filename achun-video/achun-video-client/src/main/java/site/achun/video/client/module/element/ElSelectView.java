package site.achun.video.client.module.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/15 17:12
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElSelectView implements Serializable {
    @Schema(title = "列表编码")
    private String value;
    @Schema(title = "列表名称")
    private String label;
    @Schema(title = "是否禁用")
    private Boolean disabled;
    @Schema(title = "选项")
    private List<ElSelectView> options;
}
