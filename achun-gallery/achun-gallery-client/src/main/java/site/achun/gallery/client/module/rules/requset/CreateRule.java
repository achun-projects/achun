package site.achun.gallery.client.module.rules.requset;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateRule implements Serializable {

    @Schema(description = "规则编码")
    private String ruleCode;

    @Schema(description = "用户编码")
    private String userCode;

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "详细描述")
    private String description;

    @Schema(description = "规则内容")
    private Object rules;
}
