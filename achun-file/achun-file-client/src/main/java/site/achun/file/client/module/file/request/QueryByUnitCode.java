package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "根据unitCode查询")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryByUnitCode implements Serializable {

    @Schema(description = "组编码")
    private String unitCode;
}
