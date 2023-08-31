package site.achun.file.client.module.storage;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.storage.request.QueryStorageByCode;
import site.achun.file.client.module.storage.request.QueryStoragePage;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@FeignClient(name = "achun-file-app", contextId = "StorageQueryClient")
public interface StorageQueryClient {

    @Operation(summary = "查询storage")
    @PostMapping("/file/storage/query-storage")
    Rsp<StorageResponse> queryStorage(@RequestBody QueryStorageByCode query);

    @Operation(summary = "分页查询storage")
    @PostMapping("/file/storage/query-storage")
    Rsp<RspPage<StorageResponse>> queryStoragePage(@RequestBody QueryStoragePage query);

}
