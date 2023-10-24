package site.achun.file.client.module.dir;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.dir.request.RequestScanDir;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "FileDirScanClient")
public interface FileDirScanClient {
    @Operation(summary = "请求扫描目录")
    @PostMapping("/file/dir/request-scan-dir")
    Rsp<Void> startScan(@RequestBody RequestScanDir req);
}
