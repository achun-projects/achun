package site.achun.user.client.module.data.request;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateUserData implements Serializable {

    private String userCode;
    private String dataCode;

    private String key;
    private Object value;
}
