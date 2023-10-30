package site.achun.updown.client.module.file;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;

import java.util.List;

@FeignClient(name = "achun-updown-app", contextId = "LocalFileInfoClient")
public interface LocalFileInfoClient {

    @Operation(summary = "获取文件夹列表")
    @PostMapping("/updown/file/get-sub-directory-list")
    Rsp<List<String>> getSubDirectoryList(@RequestBody GetSubDirsReq req);
}
