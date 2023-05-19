package site.achun.file.client.module.file.request;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryFilePage extends ReqPage implements Serializable {

    public String storageCode;
    public String bucketCodes;

}
