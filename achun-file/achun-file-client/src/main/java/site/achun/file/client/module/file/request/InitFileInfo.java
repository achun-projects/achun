package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "初始化文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InitFileInfo implements Serializable {

    private String storageCode;
    private String absolutePath;
    private String fileName;
    private String md5;
    private String size;
    private Integer type;

}
