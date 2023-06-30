package site.achun.file.client.module.file;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.configuration.FileFeignConfiguration;
import site.achun.file.client.module.file.request.*;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.response.Rsp;

import java.util.List;
import java.util.Map;

@FeignClient(name = "achun-file-app", contextId = "MediaFileQueryClient",configuration = FileFeignConfiguration.class)
public interface MediaFileQueryClient {
    @Operation(summary = "根据文件唯一标识查询文件")
    @PostMapping("/file/query/query-media-file-by-code")
    Rsp<MediaFileResponse> queryFile(@RequestBody QueryByFileCode queryByFileCode);
    @Operation(summary = "根据文件唯一标识集合查询文件Map")
    @PostMapping("/file/query/query-media-file-map-by-codes")
    Rsp<Map<String,MediaFileResponse>> queryFileMap(@RequestBody QueryByFileCodes queryByFileCodes);
    @Operation(summary = "根据单位唯一标识集合查询文件Map")
    @PostMapping("/file/query/query-media-file-map-by-unit-codes")
    Rsp<Map<String,MediaFileResponse>> queryFileMap(@RequestBody QueryByUnitCodes queryByUnitCodes);
    @Operation(summary = "根据单位唯一标识查询文件Map")
    @PostMapping("/file/query/query-media-file-map-by-unit-code")
    Rsp<Map<String,MediaFileResponse>> queryFileMap(@RequestBody QueryByUnitCode queryByUnitCode);
    @Operation(summary = "根据文件唯一标识集合查询文件List")
    @PostMapping("/file/query/query-media-file-list-by-codes")
    Rsp<List<MediaFileResponse>> queryFileList(@RequestBody QueryByFileCodes queryByFileCodes);
    @Operation(summary = "根据单位唯一标识查询文件List")
    @PostMapping("/file/query/query-media-file-list-by-unit-code")
    Rsp<List<MediaFileResponse>> queryFileList(@RequestBody QueryByUnitCode queryByUnitCode);
    @Operation(summary = "根据单位唯一标识集合查询文件List")
    @PostMapping("/file/query/query-media-file-list-by-unit-codes")
    Rsp<List<MediaFileResponse>> queryFileList(@RequestBody QueryByUnitCodes queryByUnitCodes);
}
