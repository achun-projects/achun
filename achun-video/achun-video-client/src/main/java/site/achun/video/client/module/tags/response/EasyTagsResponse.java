package site.achun.video.client.module.tags.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/9 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EasyTagsResponse implements Serializable {
    private static final long serialVersionUID = 3970620109757396984L;
    private String name;
    private LocalDateTime ctime;
    private Integer count;
}
