package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryFilePageByDirCode extends ReqPage implements Serializable {

    @Schema(description = "目录编码")
    private String dirCode;
}
