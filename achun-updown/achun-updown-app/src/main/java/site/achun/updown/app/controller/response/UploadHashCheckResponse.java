package site.achun.updown.app.controller.response;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/24 17:28
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadHashCheckResponse implements Serializable {
    private static final long serialVersionUID = -7925927969988524083L;
    private Integer type;
    private String fileCode;
}
