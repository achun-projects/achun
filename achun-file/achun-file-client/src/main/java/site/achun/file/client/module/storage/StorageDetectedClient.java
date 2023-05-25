package site.achun.file.client.module.storage;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.storage.request.RequestDetectedInStorage;

@FeignClient(name = "achun-file-app", contextId = "StorageDetectedClient")
public interface StorageDetectedClient {

    @Operation(summary = "在Storage内探测文件")
    @PostMapping("/file/storage/detected-in-storage")
    void detectedInStorage(@RequestBody RequestDetectedInStorage request);
}
