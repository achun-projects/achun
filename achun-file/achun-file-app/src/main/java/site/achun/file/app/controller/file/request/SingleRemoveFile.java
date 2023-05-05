package site.achun.file.app.controller.file.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SingleRemoveFile implements Serializable {
    private String fileCode;
}
