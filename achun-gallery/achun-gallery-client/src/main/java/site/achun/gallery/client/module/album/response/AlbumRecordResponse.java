package site.achun.gallery.client.module.album.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 *
 * @Author: Heiffeng
 * @Date: 2021/12/13 21:21
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRecordResponse implements Serializable {

    private static final long serialVersionUID = 3819915783599247574L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 相册编码
     */
    private String albumCode;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;
}
