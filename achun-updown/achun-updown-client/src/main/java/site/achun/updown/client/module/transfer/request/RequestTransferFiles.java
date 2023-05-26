package site.achun.updown.client.module.transfer.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Schema(description = "请求处理文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestTransferFiles implements Serializable {

    private Collection<String> fileCodes;
}
