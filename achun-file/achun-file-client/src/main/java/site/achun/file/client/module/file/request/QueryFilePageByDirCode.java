package site.achun.file.client.module.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryFilePageByDirCode extends ReqPage implements Serializable {

    @Schema(description = "目录编码")
    private String dirCode;

    @Schema(description = "是否只查询当前路径，为否时，查询目录下所有文件，即包含子目录内的文件")
    private Boolean onlyThis = true;
}
