package site.achun.file.client.module.storage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "创建Storage并探测文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateStorageWithDetected implements Serializable {
    private String storageRootPath;
    private String bucketCode;
    private String storageName;
}
