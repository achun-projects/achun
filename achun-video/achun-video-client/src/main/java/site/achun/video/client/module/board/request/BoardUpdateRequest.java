package site.achun.video.client.module.board.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/11 20:38
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateRequest implements Serializable {
    private static final long serialVersionUID = -827298394241808635L;

    /**
     * 相册编码
     */
    private String boardCode;
    private String userCode;

    /**
     * 画板名
     */
    private String name;

    /**
     * 画板描述
     */
    private String description;
    /**
     *
     */
    private String cover;

}
