package site.achun.file.controller.storage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.storage.StorageUpdateClient;
import site.achun.file.client.module.storage.request.CreateStorage;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.service.storage.StorageUpdateService;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "Storage更新")
@RequiredArgsConstructor
@RestController
public class StorageUpdateController implements StorageUpdateClient {

    private final StorageUpdateService storageUpdateService;

    @Override
    public Rsp<StorageResponse> createStorage(CreateStorage create) {
        return Rsp.success(storageUpdateService.createStorage(create));
    }
}
