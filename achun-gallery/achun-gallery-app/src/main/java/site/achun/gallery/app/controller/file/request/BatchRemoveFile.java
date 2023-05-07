package site.achun.gallery.app.controller.file.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class BatchRemoveFile implements Serializable {

    private Collection<String> fileCodes;
}
