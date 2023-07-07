package site.achun.updown.app.controller.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/26 16:57
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BigFileUploadMergeRequest implements Serializable {
    private static final long serialVersionUID = 1063722371031850428L;

    private Integer chunkSize;
    private String name;
    private Integer chunkTotal;
    private String hash;
    private String groupCode;
}
