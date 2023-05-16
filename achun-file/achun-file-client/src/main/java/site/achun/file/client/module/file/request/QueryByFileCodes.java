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
public class QueryByFileCodes implements Serializable {


    @Schema(description = "文件唯一标识集合")
    private Collection<String> fileCodes;
}
