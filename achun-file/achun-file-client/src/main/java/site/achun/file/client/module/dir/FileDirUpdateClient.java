package site.achun.file.client.module.dir;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.dir.request.UpdateDirByParentCode;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "FileDirUpdateClient")
public interface FileDirUpdateClient {
    @Operation(summary = "更新父类下所有目录和文件")
    @PostMapping("/file/dir/update-by-parent-code")
    Rsp<Void> update(@RequestBody UpdateDirByParentCode req);
}
