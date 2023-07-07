package site.achun.updown.app.controller.request;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/26 16:06
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BigFileUploadCheckRequest implements Serializable {

    private static final long serialVersionUID = -1236544961809228847L;
    private Integer total;
    private Integer chunkSize;
    private String hash;
}
