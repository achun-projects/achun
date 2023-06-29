package site.achun.video.client.module.album.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(title = "查询相册详情")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryAlbumDetail implements Serializable {

    private String userCode;
    private String albumCode;
}
