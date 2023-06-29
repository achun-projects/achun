package site.achun.video.client.module.group.request;

import lombok.*;

import java.io.Serializable;

/**
 * 请求删除分组
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReqRemoveEmptyGroup implements Serializable {

    private String groupCode;
    private String userCode;
}
