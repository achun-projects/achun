package site.achun.gallery.client.module.rules.requset;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryRulesPage implements Serializable {
    private String userCode;
    private ReqPage page;
}
