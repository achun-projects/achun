package site.achun.file.controller.storage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.storage.StorageQueryClient;
import site.achun.file.client.module.storage.request.ByStorageCode;
import site.achun.file.client.module.storage.request.QueryStoragePage;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.service.storage.StorageQueryService;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Tag(name = "存储查询服务")
@RequiredArgsConstructor
@RestController
public class StorageQueryController implements StorageQueryClient {

    private final StorageQueryService storageQueryService;
    @Override
    public Rsp<StorageResponse> queryStorage(ByStorageCode query) {
        return Rsp.success(storageQueryService.queryStorage(query.getCode()));
    }

    @Override
    public Rsp<RspPage<StorageResponse>> queryStoragePage(QueryStoragePage query) {
        return Rsp.success(storageQueryService.queryStoragePage(query));
    }
}
