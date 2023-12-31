package site.achun.updown.client.module.transfer.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Schema(description = "请求处理文件")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestTransferFile implements Serializable {

    private String fileCode;

}
