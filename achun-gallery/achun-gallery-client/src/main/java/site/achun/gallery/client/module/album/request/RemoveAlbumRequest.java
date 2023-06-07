package site.achun.gallery.client.module.album.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/15 22:13
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveAlbumRequest implements Serializable {

    private static final long serialVersionUID = -6483541682999248373L;
    /**
     * 相册编码
     */
    @NotEmpty
    private String albumCode;

    private String userCode;
}
