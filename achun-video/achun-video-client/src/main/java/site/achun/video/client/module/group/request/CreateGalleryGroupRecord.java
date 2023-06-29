package site.achun.video.client.module.group.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/28 10:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGalleryGroupRecord implements Serializable {
    private static final long serialVersionUID = 5277246446647801299L;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 画板或者相册编码
     */
    private String listCode;

}
