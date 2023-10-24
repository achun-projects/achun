package site.achun.file.client.module.storage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Schema(description = "在Storage内探测文件")
@Data
@ToString
public class QueryStoragePage extends ReqPage implements Serializable {

    private String bucketCode;
}
