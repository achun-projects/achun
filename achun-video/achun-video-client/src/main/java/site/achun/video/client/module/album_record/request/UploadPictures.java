package site.achun.video.client.module.album_record.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/19 10:18
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadPictures implements Serializable {
    private static final long serialVersionUID = 3159653116123650723L;

    /**
     * 相册编码，对应album表
     */
    @NotEmpty(message = "相册编码不能为空")
    private String albumCode;

    /**
     * 文件记录表，对应db_file.group表编码
     */
    @NotEmpty(message = "分组编码不能为空")
    private String setCode;

    private Set<String> tags;

    private String name; // 分组名

    private String desc;

    private Integer viewLevel;

    private String userCode;

}
