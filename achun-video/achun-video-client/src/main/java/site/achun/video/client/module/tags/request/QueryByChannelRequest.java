package site.achun.video.client.module.tags.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/9 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class QueryByChannelRequest implements Serializable {
    private static final long serialVersionUID = 2907042824909075967L;

    private String channelCode;

    private Integer limit = 20;
}
