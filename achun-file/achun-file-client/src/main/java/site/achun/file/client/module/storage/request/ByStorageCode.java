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
public class ByStorageCode implements Serializable {

    private String code;
}
