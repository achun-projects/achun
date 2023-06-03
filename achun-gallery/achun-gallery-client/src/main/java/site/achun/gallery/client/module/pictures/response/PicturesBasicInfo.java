package site.achun.gallery.client.module.pictures.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class PicturesBasicInfo implements Serializable {
    private String fileCode;
    private String url;
    private String cover;
    private Integer width;
    private Integer height;
    private Integer wh;
    private Integer duration;
    private Integer type;
}
