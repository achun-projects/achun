package site.achun.video.client.module.channel.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/12/5 17:46
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryChannelPage implements Serializable {

    private Integer page;
    private Integer size;
}
