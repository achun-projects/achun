package site.achun.gallery.client.module.board.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

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
public class BoardRecordCreateRequest implements Serializable {

    private static final long serialVersionUID = -6596039971053392439L;
    /**
     * 相册编码，对应album表
     */
    @NotEmpty(message = "相册编码不能为空")
    private String boardCode;

    private Set<String> fileCodes;
}
