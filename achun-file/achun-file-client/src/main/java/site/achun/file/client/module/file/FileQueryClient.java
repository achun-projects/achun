package site.achun.file.client.module.file;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.request.QueryByUnitCode;
import site.achun.file.client.module.file.request.QueryByUnitCodes;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.support.api.response.Rsp;

import java.util.List;
import java.util.Map;

@FeignClient(name = "achun-file-app", contextId = "FileQueryClient")
public interface FileQueryClient {
    @PostMapping("/file/query/query-file-by-code")
    Rsp<FileInfoResponse> queryFile(@RequestBody QueryByFileCode queryByFileCode);
    @PostMapping("/file/query/query-file-map-by-codes")
    Rsp<Map<String,FileInfoResponse>> queryFileMap(@RequestBody QueryByFileCodes queryByFileCodes);
    @PostMapping("/file/query/query-file-map-by-unit-codes")
    Rsp<Map<String,FileInfoResponse>> queryFileMap(@RequestBody QueryByUnitCodes queryByUnitCodes);
    @PostMapping("/file/query/query-file-map-by-unit-code")
    Rsp<Map<String,FileInfoResponse>> queryFileMap(@RequestBody QueryByUnitCode queryByUnitCode);
    @PostMapping("/file/query/query-file-list-by-codes")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByFileCodes queryByFileCodes);
    @PostMapping("/file/query/query-file-list-by-unit-code")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByUnitCode queryByUnitCode);
    @PostMapping("/file/query/query-file-list-by-unit-codes")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByUnitCodes queryByUnitCodes);
}
