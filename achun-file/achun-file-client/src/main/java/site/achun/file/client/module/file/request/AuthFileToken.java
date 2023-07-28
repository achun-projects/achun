package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "校验文件token")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthFileToken implements Serializable {
    private String token;
}
