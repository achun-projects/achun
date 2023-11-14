package site.achun.file.client.module.file;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.configuration.FileFeignConfiguration;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.file.request.*;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.Map;

@FeignClient(name = "achun-file-app", contextId = "FileQueryClient",configuration = FileFeignConfiguration.class)
public interface FileQueryClient {
    @Operation(summary = "根据文件唯一标识查询文件")
    @PostMapping("/file/query/query-file-by-code")
    Rsp<FileInfoResponse> queryFile(@RequestBody QueryByFileCode queryByFileCode);
    @Operation(summary = "根据文件唯一标识集合查询文件Map")
    @PostMapping("/file/query/query-file-map-by-codes")
    Rsp<Map<String,FileInfoResponse>> queryFileMap(@RequestBody QueryByFileCodes queryByFileCodes);
    @Operation(summary = "根据单位唯一标识集合查询文件Map")
    @PostMapping("/file/query/query-file-map-by-unit-codes")
    Rsp<Map<String,FileInfoResponse>> queryFileMap(@RequestBody QueryByUnitCodes queryByUnitCodes);
    @Operation(summary = "根据单位唯一标识查询文件Map")
    @PostMapping("/file/query/query-file-map-by-unit-code")
    Rsp<Map<String,FileInfoResponse>> queryFileMap(@RequestBody QueryByUnitCode queryByUnitCode);
    @Operation(summary = "根据文件唯一标识集合查询文件List")
    @PostMapping("/file/query/query-file-list-by-codes")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByFileCodes queryByFileCodes);
    @Operation(summary = "根据单位唯一标识查询文件List")
    @PostMapping("/file/query/query-file-list-by-unit-code")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByUnitCode queryByUnitCode);
    @Operation(summary = "根据单位唯一标识查询文件List")
    @PostMapping("/file/query/query-file-list-by-dir-code")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByDirCode queryByDirCode);
    @Operation(summary = "根据MD5查询文件List")
    @PostMapping("/file/query/query-file-list-by-md5")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByMD5 query);
    @Operation(summary = "根据单位唯一标识集合查询文件List")
    @PostMapping("/file/query/query-file-list-by-unit-codes")
    Rsp<List<FileInfoResponse>> queryFileList(@RequestBody QueryByUnitCodes queryByUnitCodes);
    @Operation(summary = "查询文件分页")
    @PostMapping("/file/query/query-file-local-info-page")
    Rsp<RspPage<FileLocalInfoResponse>> queryFileLocalInfoPage(@RequestBody QueryFilePage query);
    @Operation(summary = "查询文件本地信息")
    @PostMapping("/file/query/query-file-local-info-by-code")
    Rsp<FileLocalInfoResponse> queryFileLocalInfo(@RequestBody QueryByFileCode query);
    @Operation(summary = "查询文件本地信息")
    @PostMapping("/file/query/query-file-local-info-list-by-codes")
    Rsp<List<FileLocalInfoResponse>> queryFileLocalInfoList(@RequestBody QueryByFileCodes query);

    @Operation(summary = "根据目录查询文件List")
    @PostMapping("/file/query/query-file-page-by-dir")
    Rsp<RspPage<FileInfoResponse>> queryFilePage(@RequestBody QueryFilePageByDirCode query);

}
