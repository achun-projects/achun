package site.achun.user.client.module.login.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse implements Serializable {

    private String userCode;
    private String token;

}
