package site.achun.file.controller.storage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.storage.StorageUpdateClient;
import site.achun.file.client.module.storage.request.CreateStorageWithDetected;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.service.storage.StorageQueryService;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "存储单位探测服务")
@RequiredArgsConstructor
@RestController
public class StorageUpdateController implements StorageUpdateClient {

    private final StorageQueryService storageQueryService;
    @Override
    public Rsp<StorageResponse> createStorageWithDetected(CreateStorageWithDetected create) {
        log.info("createStorageWithDetected: {}", create);
        // 校验storage是否已存在
        StorageResponse storage = storageQueryService.queryStorage(create.getBucketCode(), create.getStorageName());
        if(storage != null){
            return Rsp.error("存储单位存在");
        }

        return null;
    }
}
