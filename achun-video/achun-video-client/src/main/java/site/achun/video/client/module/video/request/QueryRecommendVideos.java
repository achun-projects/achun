package site.achun.video.client.module.video.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/18 16:21
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryRecommendVideos implements Serializable {
    private static final long serialVersionUID = -1427830737605719762L;

    private String channelCode;
    private String videoCode;
    private Integer limit = 30;

}
