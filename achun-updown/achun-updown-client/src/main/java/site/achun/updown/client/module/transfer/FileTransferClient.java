package site.achun.updown.client.module.transfer;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.updown.client.module.transfer.request.RequestTransferFile;
import site.achun.updown.client.module.transfer.request.RequestTransferFiles;

@FeignClient(name = "achun-updown-app", contextId = "FileTransferClient")
public interface FileTransferClient {

    @Operation(summary = "处理文件")
    @PostMapping("/updown/transfer/transfer-file")
    void transferFile(@RequestBody RequestTransferFile request);

    @Operation(summary = "处理文件")
    @PostMapping("/updown/transfer/batch-transfer-file")
    void transferFiles(@RequestBody RequestTransferFiles request);

}
