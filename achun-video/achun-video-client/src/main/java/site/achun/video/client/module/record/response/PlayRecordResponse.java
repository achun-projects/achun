package site.achun.video.client.module.record.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/30 22:37
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayRecordResponse implements Serializable {
    private static final long serialVersionUID = 8605305675334368862L;

    // 主键
    private Integer id;

    // 用户编码
    private String userCode;

    // 视频文件编码
    private String videoFileCode;

    // 播放时长
    private Integer playLongTime;

    // 视频播放位置
    private Integer videoIndex;

    // 创建时间
    private LocalDateTime ctime;

}
