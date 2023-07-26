package site.achun.user.app.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCacheInfo implements Serializable {
    private String userCode;
    private String token;
    private Long timeout;
}
