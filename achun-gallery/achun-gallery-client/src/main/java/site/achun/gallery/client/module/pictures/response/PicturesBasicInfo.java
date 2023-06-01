package site.achun.gallery.client.module.pictures.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PicturesBasicInfo implements Serializable {
    private String fileCode;
    private String url;
    private String cover;
}
