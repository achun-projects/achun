package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Schema(description = "创建文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryByUnitCodes implements Serializable {

    @Schema(description = "组编码集合")
    private Collection<String> unitCode;
}
