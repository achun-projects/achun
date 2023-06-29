package site.achun.video.client.module.album.request;

import lombok.*;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2023/3/3 17:43
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryAlbumPage implements Serializable {

    private ReqPage reqPage;
    private String userCode;
    private String groupCode;
    private String likeName;
}
