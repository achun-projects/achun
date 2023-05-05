package site.achun.file.app.controller.file;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.api.modules.file.response.DeleteFileResponse;
import site.achun.file.app.controller.file.request.BatchRemoveFile;
import site.achun.file.app.controller.file.request.SingleRemoveFile;
import site.achun.file.app.service.file.FileUpdateService;
import site.achun.support.api.response.Rsp;

import java.util.List;

@Tag(name = "文件增删改")
@Order(22)
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileUpdateController {

    private final FileUpdateService fileUpdateService;

    @Operation(summary = "删除文件")
    @PostMapping("/file/file-update/remove-file")
    public Rsp<DeleteFileResponse> removeFile(@RequestBody SingleRemoveFile request){
        return fileUpdateService.removeFile(request.getFileCode());
    }

    @Operation(summary = "批量删除文件")
    @PostMapping("/file/file-update/batch-remove-file")
    public Rsp<List<DeleteFileResponse>> batchRemoveFile(@RequestBody BatchRemoveFile request){
        return fileUpdateService.removeFile(request.getFileCodes());
    }


}
