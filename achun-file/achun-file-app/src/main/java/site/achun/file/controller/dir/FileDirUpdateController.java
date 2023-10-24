package site.achun.file.controller.dir;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.dir.FileDirUpdateClient;
import site.achun.file.client.module.dir.request.UpdateDirByParentCode;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "目录更新")
@RequiredArgsConstructor
@RestController
public class FileDirUpdateController implements FileDirUpdateClient {
    @Override
    public Rsp<Void> update(UpdateDirByParentCode req) {
        return null;
    }
}
