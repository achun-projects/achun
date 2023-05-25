package site.achun.file.client.module.storage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


@Schema(description = "在Storage内探测文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetectedInStorage implements Serializable {

    @Schema(description = "在Storage内的path", example = "/folder1/")
    public String inStoragePath;

    @Schema(description = "Storage编码")
    public String storageCode;



}
