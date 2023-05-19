package site.achun.updown.client.module.detected;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;
import site.achun.updown.client.module.detected.request.QueryFileExist;

@FeignClient(name = "achun-updown-app", contextId = "FileDetectedClient")
public interface StorageDetectedClient {

    @Operation(summary = "探测文件是否存在")
    @PostMapping("/updown/detected/file-exist")
    Rsp<Boolean> fileExist(@RequestBody QueryFileExist query);

}
