package site.achun.gallery.client.module.pic_unit.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnewFileSet implements Serializable {
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

    private List<String> fileCodes;

    private Set<String> tags;

    private String name;
}
