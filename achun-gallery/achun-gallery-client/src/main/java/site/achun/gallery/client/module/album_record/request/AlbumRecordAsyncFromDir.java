package site.achun.gallery.client.module.album_record.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumRecordAsyncFromDir implements Serializable {
    private String albumCode;
}
