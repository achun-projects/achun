package site.achun.video.client.module.pictures.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadPicturesRequest implements Serializable {

    private String albumCode;
    private String setCode; // 分组编码
    private String userCode; // 用户编码
    private String name; // 分组名
    private String desc;
    private Collection<String> tags; // 分组标签

}
