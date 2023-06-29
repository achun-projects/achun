package site.achun.video.client.module.pictures.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/13 22:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo implements Serializable {
    private static final long serialVersionUID = 5338655120477658343L;
    private String fileCode;
    private String fileName;
    private String unitCode;
    private Integer type;
    private String cover;
    private String url;
    private String mediumUrl;
    private Integer width;
    private Integer height;
    private Integer duration; // 持续时长，视频音频等格式特有
}
