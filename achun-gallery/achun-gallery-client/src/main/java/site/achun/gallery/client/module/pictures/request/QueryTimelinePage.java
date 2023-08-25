package site.achun.gallery.client.module.pictures.request;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryTimelinePage extends ReqPage implements Serializable {

    private String userCode;
}
