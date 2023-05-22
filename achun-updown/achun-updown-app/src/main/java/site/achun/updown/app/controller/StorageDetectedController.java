package site.achun.updown.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.detected.LocalFileDetectedService;
import site.achun.updown.client.module.detected.StorageDetectedClient;
import site.achun.updown.client.module.detected.request.QueryFileExist;
import site.achun.updown.client.module.detected.request.RequestLoopFies;

@Slf4j
@Tag(name = "存储文件发现服务", description = "存储检测")
@RestController
@RequiredArgsConstructor
public class StorageDetectedController implements StorageDetectedClient {

    private final LocalFileDetectedService localFileDetectedService;

    @Override
    public Rsp<Boolean> fileExist(QueryFileExist query) {
        String pathString = query.getStorageRootPath() + query.getInStoragePath();
        return Rsp.success(localFileDetectedService.existFile(pathString));
    }

    @Override
    public void asyncLoopAllFiles(RequestLoopFies request) {
        localFileDetectedService.detected(request);
    }
}
