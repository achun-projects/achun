package site.achun.video.client.module.group.request;

import lombok.*;

import java.io.Serializable;

/**
 * 根据编码查询分组
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryGroupByCode implements Serializable {
    private String groupCode;
    private String userCode;
}
