package site.achun.video.client.module.record.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

/**
 * 播放记录新增
 *
 * @Author: Heiffeng
 * @Date: 2022/3/30 22:25
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPlayRecord implements Serializable {
    // 用户编码
    private String userCode;
    // 视频文件编码
    @NotEmpty(message = "视频文件编码不能为空")
    private String videoFileCode;
    // 播放时长
    private Integer playLongTime;
    // 视频播放位置
    private Integer videoIndex;
}
