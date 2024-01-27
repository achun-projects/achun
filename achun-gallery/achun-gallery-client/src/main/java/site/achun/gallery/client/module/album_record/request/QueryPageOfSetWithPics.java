package site.achun.gallery.client.module.album_record.request;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryPageOfSetWithPics implements Serializable {

    private ReqPage reqPage;
    private String albumCode;

}
