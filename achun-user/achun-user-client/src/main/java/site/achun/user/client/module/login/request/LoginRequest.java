package site.achun.user.client.module.login.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest implements Serializable {

    @Schema(title = "用户名")
    private String account;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "超时时间")
    private Long timeout;

}
