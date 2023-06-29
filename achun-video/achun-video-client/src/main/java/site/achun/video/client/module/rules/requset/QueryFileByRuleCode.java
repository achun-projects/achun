package site.achun.video.client.module.rules.requset;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryFileByRuleCode implements Serializable {

    @Schema(description = "规则编码")
    private String ruleCode;

}
