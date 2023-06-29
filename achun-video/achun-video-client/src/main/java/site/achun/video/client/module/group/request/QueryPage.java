package site.achun.video.client.module.group.request;

import lombok.*;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

/**
 * 查询分页参数
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryPage implements Serializable {

    private ReqPage reqPage;

    private String likeName;

    private String userCode;

    /**
     * 类型，1-相册，2-画板
     */
    private Integer type;

}
