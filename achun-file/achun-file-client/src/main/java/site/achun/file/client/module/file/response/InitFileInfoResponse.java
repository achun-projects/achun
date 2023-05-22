package site.achun.file.client.module.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.file.request.InitFileInfo;

import java.io.Serializable;

@Schema(description = "初始化文件返回")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InitFileInfoResponse extends InitFileInfo implements Serializable {

    private String fileCode;
}
