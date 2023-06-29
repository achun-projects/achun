package site.achun.video.client.module.album.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/14 18:30
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRecordCreateRequest implements Serializable {
    private static final long serialVersionUID = 999243182679230376L;


    /**
     * 相册编码，对应album表
     */
    @NotEmpty(message = "相册编码不能为空")
    private String albumCode;

    /**
     * 文件记录表，对应db_file.group表编码
     */
    @NotEmpty(message = "分组编码不能为空")
    private String groupCode;

    private String userCode;

}
