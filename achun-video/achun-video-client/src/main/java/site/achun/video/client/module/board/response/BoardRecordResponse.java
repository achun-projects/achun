package site.achun.video.client.module.board.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/7 11:47
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRecordResponse implements Serializable {
    private static final long serialVersionUID = -612959997324395736L;
    /**
     * 画板记录编码
     */
    private Integer id;

    /**
     * 画板编码，对应album表
     */
    private String boardCode;

    /**
     * 文件记录表，对应db_file.group表编码
     */
    private String fileCode;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;
}
