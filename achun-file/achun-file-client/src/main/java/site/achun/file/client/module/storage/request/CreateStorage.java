package site.achun.file.client.module.storage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "创建Storage")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateStorage implements Serializable {
    private String storageRootPath;
    private String bucketCode;
    private String storageName;
}
