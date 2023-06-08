package site.achun.file.client.module.file;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.file.request.DeleteFileRequest;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.support.api.response.Rsp;

import java.util.Collection;

@FeignClient(name = "achun-file-app", contextId = "FileUpdateClient")
public interface FileUpdateClient {

    @Operation(summary = "初始化文件")
    @PostMapping("/file/update/init-file-info")
    Rsp<InitFileInfoResponse> initFileInfo(@RequestBody InitFileInfo init);

    @Operation(summary = "更新文件")
    @PostMapping("/file/update/update-file-info")
    Rsp<FileLocalInfoResponse> updateFileInfo(@RequestBody UpdateFileRequest request);

    @Operation(summary = "逻辑删除文件")
    @PostMapping("/file/update/delete-file-by-code")
    Rsp<Boolean> deleteFileInfo(@RequestBody DeleteFileRequest request);


    @Operation(summary = "批量逻辑删除文件")
    @PostMapping("/file/update/delete-file-by-codes")
    Rsp<Boolean> deleteFileInfos(@RequestBody DeleteFileRequest request);

}
