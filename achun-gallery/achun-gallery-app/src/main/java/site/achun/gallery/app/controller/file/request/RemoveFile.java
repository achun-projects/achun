package site.achun.gallery.app.controller.file.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RemoveFile implements Serializable {
    private String fileCode;
}
