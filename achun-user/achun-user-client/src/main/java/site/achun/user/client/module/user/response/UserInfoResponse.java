package site.achun.user.client.module.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoResponse implements Serializable {
    @Schema(title = "用户编码")
    private String userCode;
    @Schema(title = "名称")
    private String name;
    @Schema(title = "账号")
    private String account;
    @Schema(title = "头像封面")
    private String cover;
}
