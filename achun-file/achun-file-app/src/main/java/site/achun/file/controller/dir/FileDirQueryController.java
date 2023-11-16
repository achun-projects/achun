package site.achun.file.controller.dir;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.dir.FileDirQueryClient;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.dir.request.ByStorageAndPath;
import site.achun.file.client.module.dir.response.DirResponse;
import site.achun.file.service.dir.FileDirQueryService;
import site.achun.support.api.response.Rsp;

import java.util.List;

@Slf4j
@Tag(name = "目录查询")
@RequiredArgsConstructor
@RestController
public class FileDirQueryController implements FileDirQueryClient {

    private final FileDirQueryService fileDirQueryService;

    @Override
    public Rsp<List<DirResponse>> querySub(ByDirCode req) {
        return Rsp.success(fileDirQueryService.querySub(req));
    }

    @Override
    public Rsp<List<DirResponse>> queryDeep(ByDirCode req) {
        return Rsp.success(fileDirQueryService.queryDeep(req));
    }

    @Override
    public Rsp<DirResponse> queryBy(ByDirCode req) {
        return Rsp.success(fileDirQueryService.query(req));
    }

    @Override
    public Rsp<DirResponse> queryBy(ByStorageAndPath req) {
        return null;
    }
}
