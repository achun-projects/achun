package site.achun.file.controller.storage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.storage.StorageDetectedClient;
import site.achun.file.client.module.storage.request.RequestDetectedInStorage;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;
import site.achun.updown.client.module.detected.UpdownDetectedClient;
import site.achun.updown.client.module.detected.request.RequestLoopAndInitFiles;

import java.nio.file.Path;

@Slf4j
@Tag(name = "存储单位探测服务")
@RequiredArgsConstructor
@RestController
public class StorageDetectedController implements StorageDetectedClient {

    private final UpdownDetectedClient updownDetectedClient;
    private final StorageService storageService;
    @Override
    public void detectedInStorage(RequestDetectedInStorage request) {
        Storage storage = storageService.getStorage(request.storageCode);
        Path localPath = Path.of(storage.getPath(), request.getInStoragePath());
        RequestLoopAndInitFiles requestLoopAndInitFiles = RequestLoopAndInitFiles.builder()
                .storageCode(request.storageCode)
                .localPath(localPath.toString())
                .build();
        updownDetectedClient.asyncLoopAndInitFiles(requestLoopAndInitFiles);
    }
}
