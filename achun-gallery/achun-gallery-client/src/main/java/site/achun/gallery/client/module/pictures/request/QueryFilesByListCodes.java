package site.achun.gallery.client.module.pictures.request;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;
import java.util.Collection;

@Data
public class QueryFilesByListCodes extends ReqPage implements Serializable {

    private Collection<String> listCodes;
}
