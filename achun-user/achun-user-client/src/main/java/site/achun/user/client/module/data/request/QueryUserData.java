package site.achun.user.client.module.data.request;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryUserData implements Serializable {

    private String userCode;
    private String key;
}
