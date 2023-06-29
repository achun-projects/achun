package site.achun.video.client.module.album.request;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/7 16:42
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlbumCreateRequest implements Serializable {
    private static final long serialVersionUID = 2215177372510956836L;

    /**
     * 相册名
     */
    private String name;

    /**
     * 相册描述
     */
    private String description;

    /**
     * 来源
     */
    private String source;

    /**
     * 分组编码集合
     */
    private Set<String> groupCodes;

    private String coverFileCode;

    /**
     * 用户编码
     */
    private String userCode;

    private String albumCode;

}
