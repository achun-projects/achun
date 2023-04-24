package site.achun.updown.api.modules.module1.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Schema(description = "用户信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    @Schema(description = "用户编码")
    private String name;

    @Schema(description = "用户编码")
    private String phone;
}
