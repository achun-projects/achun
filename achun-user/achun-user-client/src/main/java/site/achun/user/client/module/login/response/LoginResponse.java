package site.achun.user.client.module.login.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse implements Serializable {

    @Schema(title = "用户编码")
    private String userCode;
    @Schema(title = "satoken")
    private String satoken;

}
