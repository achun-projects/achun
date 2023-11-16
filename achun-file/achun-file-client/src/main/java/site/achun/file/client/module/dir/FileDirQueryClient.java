package site.achun.file.client.module.dir;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.dir.request.ByStorageAndPath;
import site.achun.file.client.module.dir.response.DirResponse;
import site.achun.support.api.response.Rsp;

import java.util.List;

@FeignClient(name = "achun-file-app", contextId = "FileDirQueryClient")
public interface FileDirQueryClient {
    @Operation(summary = "根据目录编码查询所有文件和目录")
    @PostMapping("/file/dir/query-sub-by-dir-code")
    Rsp<List<DirResponse>> querySub(@RequestBody ByDirCode req);

    @Operation(summary = "根据目录编码遍历查询文件和目录")
    @PostMapping("/file/dir/query-deep-by-dir-code")
    Rsp<List<DirResponse>> queryDeep(@RequestBody ByDirCode req);

    @Operation(summary = "查询目录详情")
    @PostMapping("/file/dir/query-dir-by-dir-code")
    Rsp<DirResponse> queryBy(@RequestBody ByDirCode req);
    @Operation(summary = "查询目录详情")
    @PostMapping("/file/dir/query-dir-by-storage-and-path")
    Rsp<DirResponse> queryBy(@RequestBody ByStorageAndPath req);



}
