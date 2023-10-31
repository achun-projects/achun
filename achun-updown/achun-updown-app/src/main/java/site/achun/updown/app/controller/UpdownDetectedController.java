package site.achun.updown.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.detected.LocalFileDetectedService;
import site.achun.updown.client.module.detected.UpdownDetectedClient;
import site.achun.updown.client.module.detected.request.QueryFileExist;
import site.achun.updown.client.module.detected.request.RequestLoopAndInitFiles;

@Slf4j
@Tag(name = "存储文件发现服务", description = "存储检测")
@RestController
@RequiredArgsConstructor
public class UpdownDetectedController implements UpdownDetectedClient {

    private final LocalFileDetectedService localFileDetectedService;

    @Override
    public Rsp<Boolean> fileExist(QueryFileExist query) {
        String pathString = query.getStorageRootPath() + query.getInStoragePath();
        return Rsp.success(localFileDetectedService.existFile(pathString));
    }

    @Override
    @Deprecated
    public void asyncLoopAndInitFiles(RequestLoopAndInitFiles request) {
        localFileDetectedService.detected(request);
    }

    @Override
    public void scanFilesByDirCode(ByDirCode dirCode) {
        localFileDetectedService.scanFilesByDirCode(dirCode);
    }
}
