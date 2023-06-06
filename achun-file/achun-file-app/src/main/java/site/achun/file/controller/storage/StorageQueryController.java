package site.achun.file.controller.storage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.storage.StorageQueryClient;
import site.achun.file.client.module.storage.request.QueryStorageByCode;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.service.storage.StorageQueryService;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "存储查询服务")
@RequiredArgsConstructor
@RestController
public class StorageQueryController implements StorageQueryClient {

    private final StorageQueryService storageQueryService;
    @Override
    public Rsp<StorageResponse> queryStorage(QueryStorageByCode query) {
        return Rsp.success(storageQueryService.queryStorage(query.getCode()));
    }
}
