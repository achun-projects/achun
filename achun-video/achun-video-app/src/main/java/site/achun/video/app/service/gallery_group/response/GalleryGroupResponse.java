package site.achun.video.app.service.gallery_group.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GalleryGroupResponse implements Serializable {
    private String groupNames;
    private List<String> groupCodes;

    private String groupCode;
}
