package site.achun.file.client.module.storage;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.storage.request.CreateStorageWithDetected;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "StorageCreateClient")
public interface StorageUpdateClient {

    @Operation(summary = "创建存储单位并探测文件")
    @PostMapping("/file/storage/create-storage-and-detected")
    Rsp<StorageResponse> createStorageWithDetected(@RequestBody CreateStorageWithDetected create);

}
