package site.achun.video.client.module.board.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/12 16:18
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveBoardRecordRequest implements Serializable {
    private static final long serialVersionUID = 1181920630581735075L;

    // 文件编码
    private String fileCode;

    // 是否包含分组
    private Boolean withGroup = Boolean.FALSE;

    // 从哪儿来
    private String from;

    // 到哪儿去
    private String to;


}
