package site.achun.file.api.modules.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "删除文件返回")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFileResponse implements Serializable {

    private String fileCode;

    private String unitCode;

    // 文件存在且被删除
    private Boolean fileExistAndDeleted;

    private String message;
}
