package site.achun.video.client.module.tags.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/17 18:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class QueryByPlistCodeRequest implements Serializable {

    private String plistCode;

    private Integer limit = 20;

}
