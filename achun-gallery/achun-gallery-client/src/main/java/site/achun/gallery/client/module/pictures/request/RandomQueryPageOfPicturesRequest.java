package site.achun.gallery.client.module.pictures.request;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class RandomQueryPageOfPicturesRequest implements Serializable {

    // 分页
    private ReqPage reqPage;

}
