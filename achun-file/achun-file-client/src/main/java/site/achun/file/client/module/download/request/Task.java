package site.achun.file.client.module.download.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "下载任务")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    public final static String KEY = "FILE:DOWNLOAD:TASK";

    private String fileCode;
    private String thirdId;
    private String url;
    private String storage;
    private String key;
    private String unit;
    private Boolean override;

}
