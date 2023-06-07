package site.achun.gallery.client.module.group.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/28 10:24
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GalleryGroupResponse implements Serializable {
    private static final long serialVersionUID = 8069104688100181859L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 画板或者相册编码
     */
    private String listCode;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

}
