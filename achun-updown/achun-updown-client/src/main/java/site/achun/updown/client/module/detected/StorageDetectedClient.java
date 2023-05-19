package site.achun.updown.client.module.detected;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;
import site.achun.updown.client.module.detected.request.QueryFileExist;

@FeignClient(name = "achun-updown-app", contextId = "FileDetectedClient")
public interface StorageDetectedClient {

    @Operation(summary = "根据单位唯一标识查询文件Map")
    @PostMapping("/updown/detected/file-exist")
    Rsp<Boolean> fileExist(@RequestBody QueryFileExist query);

}
