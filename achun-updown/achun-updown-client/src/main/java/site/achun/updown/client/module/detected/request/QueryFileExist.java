package site.achun.updown.client.module.detected.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "查询文件是否存在")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryFileExist implements Serializable {

    private String storageRootPath;
    private String inStoragePath;

}
