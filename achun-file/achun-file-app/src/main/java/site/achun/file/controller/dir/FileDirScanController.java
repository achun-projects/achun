package site.achun.file.controller.dir;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.dir.FileDirScanClient;
import site.achun.file.client.module.dir.request.RequestScanDir;
import site.achun.support.api.response.Rsp;
@Slf4j
@Tag(name = "目录扫描")
@RequiredArgsConstructor
@RestController
public class FileDirScanController implements FileDirScanClient {
    @Override
    public Rsp<Void> startScan(RequestScanDir req) {
        return null;
    }
}
